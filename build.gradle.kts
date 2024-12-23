// إعدادات البناء لمشروع Manga Akojdad

buildscript {
    dependencies {
        // مكتبة مخصصة لاختصارات التطبيق
        classpath(libs.android.shortcut.gradle)
    }
}

plugins {
    // مكوّن إضافي لتسلسل البيانات JSON
    alias(kotlinx.plugins.serialization) apply false
    // مكتبة لإدارة تراخيص المكتبات المستخدمة في التطبيق
    alias(libs.plugins.aboutLibraries) apply false
    // مكوّن إضافي لدعم Firebase Crashlytics لتحليل الأخطاء
    alias(libs.plugins.firebase.crashlytics) apply false
    // مكوّن لإضافة خدمات Google
    alias(libs.plugins.google.services) apply false
    // مكوّن إضافي لدعم الترجمات في التطبيق
    alias(libs.plugins.moko) apply false
    // مكتبة لإدارة قواعد بيانات SQLDelight
    alias(libs.plugins.sqldelight) apply false
}

android {
    namespace = "com.mangaakojdad.app" // معرف التطبيق
    compileSdk = 34 // إصدار Android SDK المستخدم
    defaultConfig {
        applicationId = "com.mangaakojdad.app" // معرف التطبيق لتجنب التعارض
        minSdk = 21 // الحد الأدنى للإصدار المدعوم
        targetSdk = 34 // الإصدار المستهدف
        versionCode = 1 // رقم الإصدار
        versionName = "1.0" // اسم الإصدار
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory) // تنظيف ملفات البناء القديمة
}