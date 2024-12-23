package mihon.domain.extensionrepo.exception

import java.io.IOException

/**
 * استثناء يُستخدم للتعامل مع أخطاء حفظ المستودع في قاعدة البيانات.
 * يتم تجريد الاستثناءات المتعلقة ب SQLiteException و SQLiteConstraintException لدعم الأنظمة متعددة المنصات.
 *
 * @param throwable الاستثناء المصدر الذي يتضمنه تتبع الأخطاء.
 */
class SaveExtensionRepoException(throwable: Throwable) : IOException("خطأ في حفظ المستودع في قاعدة البيانات", throwable)