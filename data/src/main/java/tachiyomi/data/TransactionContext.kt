package mangaakojdad.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume

internal suspend fun MangaDatabaseHandler.getCurrentDatabaseContext(): CoroutineContext {
    return coroutineContext[TransactionElement]?.transactionDispatcher ?: queryDispatcher
}

internal suspend fun <T> MangaDatabaseHandler.withTransaction(block: suspend () -> T): T {
    val transactionContext =
        coroutineContext[TransactionElement]?.transactionDispatcher ?: createTransactionContext()
    return withContext(transactionContext) {
        val transactionElement = coroutineContext[TransactionElement]!!
        transactionElement.acquire()
        try {
            db.transactionWithResult {
                runBlocking(transactionContext) {
                    block()
                }
            }
        } finally {
            transactionElement.release()
        }
    }
}

private suspend fun MangaDatabaseHandler.createTransactionContext(): CoroutineContext {
    val controlJob = Job()
    coroutineContext[Job]?.invokeOnCompletion {
        controlJob.cancel()
    }

    val dispatcher = transactionDispatcher.acquireTransactionThread(controlJob)
    val transactionElement = TransactionElement(controlJob, dispatcher)
    val threadLocalElement =
        suspendingTransactionId.asContextElement(System.identityHashCode(controlJob))
    return dispatcher + transactionElement + threadLocalElement
}

private suspend fun CoroutineDispatcher.acquireTransactionThread(
    controlJob: Job,
): ContinuationInterceptor {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            controlJob.cancel()
        }
        try {
            dispatch(EmptyCoroutineContext) {
                runBlocking {
                    continuation.resume(coroutineContext[ContinuationInterceptor]!!)
                    controlJob.join()
                }
            }
        } catch (ex: RejectedExecutionException) {
            continuation.cancel(
                IllegalStateException(
                    "Unable to acquire a thread to perform the database transaction",
                    ex,
                ),
            )
        }
    }
}

private class TransactionElement(
    private val transactionThreadControlJob: Job,
    val transactionDispatcher: ContinuationInterceptor,
) : CoroutineContext.Element {

    companion object Key : CoroutineContext.Key<TransactionElement>

    override val key: CoroutineContext.Key<TransactionElement>
        get() = TransactionElement

    private val referenceCount = AtomicInteger(0)

    fun acquire() {
        referenceCount.incrementAndGet()
    }

    fun release() {
        val count = referenceCount.decrementAndGet()
        if (count < 0) {
            throw IllegalStateException("Transaction was never started or was already released")
        } else if (count == 0) {
            transactionThreadControlJob.cancel()
        }
    }
}