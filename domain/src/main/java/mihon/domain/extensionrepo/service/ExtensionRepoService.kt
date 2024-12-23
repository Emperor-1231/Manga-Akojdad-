package mihon.domain.extensionrepo.service

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.network.awaitSuccess
import eu.kanade.tachiyomi.network.parseAs
import kotlinx.serialization.json.Json
import logcat.LogPriority
import mihon.domain.extensionrepo.model.ExtensionRepo
import tachiyomi.core.common.util.lang.withIOContext
import tachiyomi.core.common.util.system.logcat

/**
 * خدمة التعامل مع مستودعات الإضافات ضمن تطبيق Manga Akojdad.
 * تتضمن استدعاء البيانات الخاصة بالمستودعات من الشبكة وتحويلها إلى نماذج البيانات.
 */
class ExtensionRepoService(
    networkHelper: NetworkHelper,
    private val json: Json,
) {
    val client = networkHelper.client

    /**
     * جلب تفاصيل المستودع من الرابط المحدد.
     * @param repo رابط المستودع.
     * @return نموذج بيانات المستودع (ExtensionRepo) إذا تم العثور عليه، أو `null` إذا حدث خطأ.
     */
    suspend fun fetchRepoDetails(
        repo: String,
    ): ExtensionRepo? {
        return withIOContext {
            try {
                with(json) {
                    // تنفيذ طلب GET للحصول على بيانات المستودع من الرابط المحدد.
                    client.newCall(GET("$repo/repo.json"))
                        .awaitSuccess() // الانتظار حتى تنجح الاستجابة.
                        .parseAs<ExtensionRepoMetaDto>() // تحويل البيانات المستلمة إلى نموذج DTO.
                        .toExtensionRepo(baseUrl = repo) // تحويل الـ DTO إلى نموذج البيانات (ExtensionRepo).
                }
            } catch (e: Exception) {
                // في حال حدوث أي استثناء أو خطأ أثناء جلب البيانات، يتم تسجيل الخطأ.
                logcat(LogPriority.ERROR, e) { "فشل في جلب تفاصيل المستودع" }
                null
            }
        }
    }
}