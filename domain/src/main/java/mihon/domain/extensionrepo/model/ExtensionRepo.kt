package mihon.domain.extensionrepo.model

/**
 * نموذج بيانات يمثل مستودع الإضافات في تطبيق Manga Akojdad.
 *
 * @param baseUrl عنوان الموقع الأساسي للمستودع.
 * @param name اسم المستودع.
 * @param shortName الاسم القصير للمستودع (اختياري).
 * @param website رابط الموقع الإلكتروني للمستودع.
 * @param signingKeyFingerprint بصمة المفتاح الخاص بالمستودع.
 */
data class ExtensionRepo(
    val baseUrl: String,
    val name: String,
    val shortName: String?,
    val website: String,
    val signingKeyFingerprint: String,
)