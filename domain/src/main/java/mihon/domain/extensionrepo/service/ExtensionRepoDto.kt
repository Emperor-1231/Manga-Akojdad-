package mihon.domain.extensionrepo.service

import kotlinx.serialization.Serializable
import mihon.domain.extensionrepo.model.ExtensionRepo

/**
 * DTO (Data Transfer Object) لتمثيل بيانات المستودع الميتا (الميتا داتا) ضمن تطبيق Manga Akojdad.
 */
@Serializable
data class ExtensionRepoMetaDto(
    val meta: ExtensionRepoDto,
)

/**
 * DTO (Data Transfer Object) لتمثيل تفاصيل المستودع ضمن تطبيق Manga Akojdad.
 * يتضمن البيانات الأساسية مثل الاسم، ورابط الموقع، وبصمة المفتاح الخاص.
 */
@Serializable
data class ExtensionRepoDto(
    val name: String, // اسم المستودع.
    val shortName: String?, // الاسم القصير للمستودع (اختياري).
    val website: String, // رابط الموقع الإلكتروني للمستودع.
    val signingKeyFingerprint: String, // بصمة المفتاح الخاص للمستودع.
)

/**
 * تحويل DTO لتمثيل المستودع إلى نموذج البيانات (ExtensionRepo) مع إضافة عنوان الموقع.
 */
fun ExtensionRepoMetaDto.toExtensionRepo(baseUrl: String): ExtensionRepo {
    return ExtensionRepo(
        baseUrl = baseUrl,
        name = meta.name,
        shortName = meta.shortName,
        website = meta.website,
        signingKeyFingerprint = meta.signingKeyFingerprint,
    )
}