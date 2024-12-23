package akojdad.domain.extensionrepo.service

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.network.awaitSuccess
import eu.kanade.tachiyomi.network.parseAs
import kotlinx.serialization.json.Json
import logcat.LogPriority
import akojdad.domain.extensionrepo.model.ExtensionRepo
import tachiyomi.core.common.util.lang.withIOContext
import tachiyomi.core.common.util.system.logcat

/**
 * خدمة إدارة مستودعات الإضافات لتطبيق Manga Akojdad.
 * تقوم بجلب بيانات المستودعات وتحويلها إلى نماذج بيانات يمكن استخدامها.
 */
class ExtensionRepoService(
    networkHelper: NetworkHelper,
    private val json: Json,
) {
    private val client = networkHelper.client

    /**
     * استرجاع تفاصيل المستودع بناءً على رابط المستودع.
     * @param repo رابط المستودع.
     * @return نموذج بيانات المستودع (ExtensionRepo) أو `null` في حالة الفشل.
     */
    suspend fun fetchRepoDetails(
        repo: String,
    ): ExtensionRepo? {
        return withIOContext {
            try {
                client.newCall(GET("$repo/repo.json"))
                    .awaitSuccess()
                    .parseAs<ExtensionRepoMetaDto>()
                    .toExtensionRepo(baseUrl = repo)
            } catch (e: Exception) {
                logcat(LogPriority.ERROR, e) { "تعذر جلب تفاصيل مستودع Manga Akojdad" }
                null
            }
        }
    }
}