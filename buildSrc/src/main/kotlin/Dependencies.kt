object Kotlin {
    const val standardLibrary = "1.4.10"
    const val coroutines = "1.3.9"
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
        const val hilt = "2.40.5"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val androidHilt = "dagger.hilt.android.plugin"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object Libraries {
    private object Versions {
        const val ktx = "1.7.0"
        const val appCompat = "1.4.1"
        const val material = "1.5.0"
        const val hilt = BuildPlugins.Versions.hilt
        const val navigation = "2.3.5"
    }

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationDynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val espressoCore = "3.2.0"
        const val testExtensions = "1.1.1"
        const val hilt = BuildPlugins.Versions.hilt
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
}