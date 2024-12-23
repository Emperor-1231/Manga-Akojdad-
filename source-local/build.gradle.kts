import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("mihon.library") // استخدام مكتبة Mihon الخاصة
    kotlin("multiplatform") // دعم Kotlin متعدد المنصات
}

kotlin {
    androidTarget() // دعم الاستهداف لـ Android
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.sourceApi) // إضافة مكتبة الـ API المصدر
                api(projects.i18n) // إضافة مكتبة الترجمة الدولية

                implementation(libs.unifile) // إضافة مكتبة Unifile للتعامل مع الملفات
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.core.archive) // إضافة مكتبة الـ Archive
                implementation(projects.core.common) // إضافة المكتبة المشتركة
                implementation(projects.coreMetadata) // إضافة مكتبة الـ Metadata

                // يمكن نقل مكتبة ChapterRecognition إلى وحدة منفصلة في المستقبل؟
                implementation(projects.domain) // إضافة مكتبة الـ Domain

                implementation(kotlinx.bundles.serialization) // إضافة مكتبة Serialization من Kotlin
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xexpect-actual-classes", // تمكين الفئات "expect/actual"
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi", // تمكين API التجربة لـ Kotlin Serialization
        )
    }
}

android {
    namespace = "com.mangaakojdad.source.local" // تغيير الـ namespace ليتناسب مع تطبيق Manga Akojdad

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // إعداد Runner لاختبارات Android
        consumerProguardFiles("consumer-rules.pro") // استخدام ملف proguard الخاص بالتطبيق
    }
}