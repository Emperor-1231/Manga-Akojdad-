package akojdad.domain.extensionrepo.interactor

import kotlinx.coroutines.flow.Flow
import akojdad.domain.extensionrepo.model.ExtensionRepo
import akojdad.domain.extensionrepo.repository.ExtensionRepoRepository

/**
 * مسؤول عن استرجاع المستودعات المخزنة.
 */
class GetExtensionRepo(
    private val repository: ExtensionRepoRepository,
) {
    /**
     * الاشتراك في جميع مستودعات الإضافات كمصدر بيانات متدفق.
     * @return قائمة محدثة من المستودعات.
     */
    fun subscribeAll(): Flow<List<ExtensionRepo>> = repository.subscribeAll()

    /**
     * استرجاع جميع مستودعات الإضافات دفعة واحدة.
     * @return قائمة بجميع المستودعات.
     */
    suspend fun getAll(): List<ExtensionRepo> = repository.getAll()
}