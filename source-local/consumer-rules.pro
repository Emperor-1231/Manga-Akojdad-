# أضف قواعد ProGuard الخاصة بتطبيقك هنا
# يمكنك التحكم في مجموعة ملفات التكوين المطبقة باستخدام
# proguardFiles في build.gradle.
#
# لمزيد من التفاصيل، انظر
#   http://developer.android.com/guide/developing/tools/proguard.html

# إذا كان تطبيقك يستخدم WebView مع JavaScript، قم بإلغاء تعليق هذا السطر
# وتحديد الاسم الكامل لفئة واجهة JavaScript:
# -keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
# }

# إذا كنت ترغب في الحفاظ على معلومات أرقام الأسطر لأغراض تصحيح الأخطاء
-keepattributes SourceFile,LineNumberTable

# إذا كنت تحافظ على معلومات رقم السطر، قم بإلغاء تعليق هذا السطر لإخفاء اسم الملف المصدر الأصلي
-renamesourcefileattribute SourceFile

# إذا كنت تستخدم مكتبة SQDelight، تأكد من أن الفئات التالية لا يتم تقليصها
-keep class com.mangaakojdad.source.local.** { *; }

# حافظ على تفاصيل الفئات الخاصة بـ Kotlin Serialization
-keep class kotlinx.serialization.** { *; }

# حافظ على الأسطر في حال تم استخدام Debugging أو تتبع الأخطاء
-keepattributes SourceFile,LineNumberTable