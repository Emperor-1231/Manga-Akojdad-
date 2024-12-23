package akojdad.domain.extensionrepo.interactor

import akojdad.domain.extensionrepo.model.ExtensionRepo
import akojdad.domain.extensionrepo.repository.ExtensionRepoRepository

/**
 * مسؤول عن استبدال مستودع بإصدار جديد أو تحديث بياناته.
 */
class ReplaceExtensionRepo(
    private val repository: ExtensionRepoRepository,
) {
    /**
     * استبدال مستودع بناءً على البيانات الجديدة.
     * @param repo نموذج بيانات المستودع الجديد.
     */
    suspend fun await(repo: ExtensionRepo) {
        repository.replaceRepo(repo)
    }
}