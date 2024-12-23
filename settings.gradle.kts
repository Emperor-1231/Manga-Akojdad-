pluginManagement {
    resolutionStrategy {
        eachPlugin {
            val androidPluginRegex = "com.android.(library|application)".toRegex()
            if (androidPluginRegex.matches(requested.id.id)) {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
    repositories {
        gradlePluginPortal() // Default Gradle plugins
        google() // For Android-specific dependencies
        mavenCentral() // Central repository
        maven(url = "https://www.jitpack.io") // Custom libraries
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("kotlinx") { from(files("gradle/kotlinx.versions.toml")) }
        create("androidx") { from(files("gradle/androidx.versions.toml")) }
        create("compose") { from(files("gradle/compose.versions.toml")) }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Google Maven
        mavenCentral() // Central repository
        maven(url = "https://www.jitpack.io") // JitPack for custom libraries
    }
}

// Enable advanced features
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Project structure
rootProject.name = "MangaAkojdad"
include(":app")
include(":core-metadata")
include(":core:archive")
include(":core:common")
include(":data")
include(":domain")
include(":i18n")
include(":macrobenchmark")
include(":presentation-core")
include(":presentation-widget")
include(":source-api")
include(":source-local")