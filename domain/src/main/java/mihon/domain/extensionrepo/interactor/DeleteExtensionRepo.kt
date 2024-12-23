package akojdad.domain.extensionrepo.interactor

import akojdad.domain.extensionrepo.repository.ExtensionRepoRepository

/**
 * مسؤول عن حذف مستودع إضافة من قاعدة البيانات.
 */
class DeleteExtensionRepo(
    private val repository: ExtensionRepoRepository,
) {
    /**
     * حذف مستودع بناءً على عنوانه الأساسي.
     * @param baseUrl الرابط الأساسي للمستودع.
     */
    suspend fun await(baseUrl: String) {
        repository.deleteRepo(baseUrl)
    }
}