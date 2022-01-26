object Kotlin {
    const val standardLibrary = "1.4.10"
}

object AndroidClient {
    const val appId = "com.rabobank.rabobankassignament"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidSdk {
    const val min = 27
    const val compile = 32
    const val target = compile
}

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "4.2.0"
        const val gradleVersion = "7.3.3"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
}

object Libraries {
    private object Versions {
        const val ktx = "1.7.0"
        const val appCompat = "1.4.1"
        const val material = "1.5.0"
    }

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val espressoCore = "3.2.0"
        const val testExtensions = "1.1.1"

    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}