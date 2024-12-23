package manga.akodjad.data.history

import kotlinx.coroutines.flow.Flow
import logcat.LogPriority
import manga.akodjad.core.common.util.system.logcat
import manga.akodjad.data.DatabaseHandler
import manga.akodjad.domain.history.model.History
import manga.akodjad.domain.history.model.HistoryUpdate
import manga.akodjad.domain.history.model.HistoryWithRelations
import manga.akodjad.domain.history.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val handler: DatabaseHandler,
) : HistoryRepository {

    override fun getHistory(query: String): Flow<List<HistoryWithRelations>> {
        return handler.subscribeToList {
            historyViewQueries.history(query, HistoryMapper::mapHistoryWithRelations)
        }
    }

    override suspend fun getLastHistory(): HistoryWithRelations? {
        return handler.awaitOneOrNull {
            historyViewQueries.getLatestHistory(HistoryMapper::mapHistoryWithRelations)
        }
    }

    override suspend fun getTotalReadDuration(): Long {
        return handler.awaitOne { historyQueries.getReadDuration() }
    }

    override suspend fun getHistoryByMangaId(mangaId: Long): List<History> {
        return handler.awaitList { historyQueries.getHistoryByMangaId(mangaId, HistoryMapper::mapHistory) }
    }

    override suspend fun resetHistory(historyId: Long) {
        try {
            handler.await { historyQueries.resetHistoryById(historyId) }
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, throwable = e)
        }
    }

    override suspend fun resetHistoryByMangaId(mangaId: Long) {
        try {
            handler.await { historyQueries.resetHistoryByMangaId(mangaId) }
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, throwable = e)
        }
    }

    override suspend fun deleteAllHistory(): Boolean {
        return try {
            handler.await { historyQueries.removeAllHistory() }
            true
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, throwable = e)
            false
        }
    }

    override suspend fun upsertHistory(historyUpdate: HistoryUpdate) {
        try {
            handler.await {
                historyQueries.upsert(
                    historyUpdate.chapterId,
                    historyUpdate.readAt,
                    historyUpdate.sessionReadDuration,
                )
            }
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, throwable = e)
        }
    }
}