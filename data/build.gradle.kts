plugins {
    id("mihon.library") // استخدام مكتبة Mihon الخاصة
    kotlin("android") // Kotlin لـ Android
    kotlin("plugin.serialization") // التفعيل لـ Kotlin Serialization
    alias(libs.plugins.sqldelight) // استخدام SQDelight لإنشاء قاعدة بيانات
}

android {
    namespace = "com.mangaakojdad.data" // تغيير الـ namespace ليتناسب مع التطبيق

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro") // إضافة ملف proguard الخاص بالتطبيق
    }

    sqldelight {
        databases {
            create("MangaDatabase") { // إنشاء قاعدة بيانات بالاسم الصحيح
                packageName.set("com.mangaakojdad.data") // تغيير الـ package name ليتناسب مع التطبيق
                dialect(libs.sqldelight.dialects.sql) // تحديد الدياكتيك الخاص بـ SQLDelight
                schemaOutputDirectory.set(project.file("./src/main/sqldelight")) // تعيين دليل المخطط
            }
        }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi") // تمكين خاصية التجربة لـ Kotlin Serialization
    }
}

dependencies {
    implementation(projects.sourceApi) // إضافة مكتبة الـ API المصدر
    implementation(projects.domain) // إضافة مكتبة الـ Domain
    implementation(projects.core.common) // إضافة المكتبة المشتركة

    api(libs.bundles.sqldelight) // إضافة مكتبة SQDelight
}