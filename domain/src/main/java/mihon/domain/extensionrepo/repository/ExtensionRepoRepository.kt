package mihon.domain.extensionrepo.repository

import kotlinx.coroutines.flow.Flow
import mihon.domain.extensionrepo.model.ExtensionRepo

/**
 * واجهة التعامل مع مستودعات الإضافات في تطبيق Manga Akojdad.
 * تُستخدم للحصول على المستودعات، حفظها، تحديثها، أو حذفها من قاعدة البيانات.
 */
interface ExtensionRepoRepository {

    /**
     * الاشتراك في جميع المستودعات المتاحة.
     * @return تدفق يحتوي على قائمة من المستودعات.
     */
    fun subscribeAll(): Flow<List<ExtensionRepo>>

    /**
     * الحصول على جميع المستودعات.
     * @return قائمة من المستودعات.
     */
    suspend fun getAll(): List<ExtensionRepo>

    /**
     * الحصول على مستودع بالإشارة إلى عنوان الموقع الأساسي.
     * @param baseUrl عنوان الموقع للمستودع.
     * @return المستودع إذا تم العثور عليه، أو `null` إذا لم يوجد.
     */
    suspend fun getRepo(baseUrl: String): ExtensionRepo?

    /**
     * الحصول على مستودع باستخدام بصمة المفتاح الخاص.
     * @param fingerprint بصمة المفتاح.
     * @return المستودع إذا تم العثور عليه، أو `null` إذا لم يوجد.
     */
    suspend fun getRepoBySigningKeyFingerprint(fingerprint: String): ExtensionRepo?

    /**
     * الحصول على عدد المستودعات.
     * @return تدفق يحتوي على العدد الإجمالي للمستودعات.
     */
    fun getCount(): Flow<Int>

    /**
     * إدخال مستودع جديد في قاعدة البيانات.
     * @param baseUrl عنوان الموقع للمستودع.
     * @param name اسم المستودع.
     * @param shortName الاسم القصير للمستودع (اختياري).
     * @param website رابط الموقع الإلكتروني للمستودع.
     * @param signingKeyFingerprint بصمة المفتاح الخاص بالمستودع.
     */
    suspend fun insertRepo(
        baseUrl: String,
        name: String,
        shortName: String?,
        website: String,
        signingKeyFingerprint: String,
    )

    /**
     * تحديث أو إضافة مستودع إلى قاعدة البيانات.
     * @param baseUrl عنوان الموقع للمستودع.
     * @param name اسم المستودع.
     * @param shortName الاسم القصير للمستودع (اختياري).
     * @param website رابط الموقع الإلكتروني للمستودع.
     * @param signingKeyFingerprint بصمة المفتاح الخاص بالمستودع.
     */
    suspend fun upsertRepo(
        baseUrl: String,
        name: String,
        shortName: String?,
        website: String,
        signingKeyFingerprint: String,
    )

    /**
     * تحديث أو إضافة مستودع باستخدام نموذج البيانات.
     * @param repo مستودع البيانات.
     */
    suspend fun upsertRepo(repo: ExtensionRepo) {
        upsertRepo(
            baseUrl = repo.baseUrl,
            name = repo.name,
            shortName = repo.shortName,
            website = repo.website,
            signingKeyFingerprint = repo.signingKeyFingerprint,
        )
    }

    /**
     * استبدال مستودع موجود بمستودع جديد.
     * @param newRepo المستودع الجديد.
     */
    suspend fun replaceRepo(newRepo: ExtensionRepo)

    /**
     * حذف مستودع باستخدام عنوان الموقع الأساسي.
     * @param baseUrl عنوان الموقع للمستودع.
     */
    suspend fun deleteRepo(baseUrl: String)
}