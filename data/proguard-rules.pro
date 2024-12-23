# أضف قواعد ProGuard الخاصة بتطبيقك هنا.
# يمكنك التحكم في مجموعة ملفات التكوين المطبقة باستخدام
# proguardFiles في build.gradle.
#
# لمزيد من التفاصيل، انظر
#   http://developer.android.com/guide/developing/tools/proguard.html

# حافظ على أسماء الفئات التي تخص SQDelight
-keep class com.mangaakojdad.data.** { *; }

# إذا كنت تستخدم الـ WebView مع JavaScript، أضف قواعد للمحافظة عليها
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
#}

# إذا كان تطبيقك يعتمد على الواجهات الخاصة بـ Kotlin Serialization
# قم بالحفاظ عليها:
-keep class kotlinx.serialization.** { *; }

# حافظ على التفاصيل الخاصة بالمخطط
-keep class com.squareup.sqldelight.** { *; }

# حافظ على الأسطر في حال تم استخدام Debugging أو تتبع الأخطاء
-keepattributes SourceFile,LineNumberTable