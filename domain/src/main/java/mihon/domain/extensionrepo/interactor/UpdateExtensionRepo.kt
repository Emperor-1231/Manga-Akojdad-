package akojdad.domain.extensionrepo.interactor

import akojdad.domain.extensionrepo.repository.ExtensionRepoRepository

/**
 * مسؤول عن حساب عدد مستودعات الإضافات.
 */
class GetExtensionRepoCount(
    private val repository: ExtensionRepoRepository,
) {
    /**
     * الاشتراك للحصول على عدد المستودعات كمصدر بيانات متدفق.
     */
    fun subscribe() = repository.getCount()
}