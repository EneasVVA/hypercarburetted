object Kotlin {
    const val standardLibrary = "1.6.10"
    const val coroutines = "1.6.0"
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
        const val hilt = "2.40.1"
        const val navigation = "2.3.5"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val androidHilt = "dagger.hilt.android.plugin"
    const val safeArgsPlugin = "androidx.navigation.safeargs.kotlin"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libraries {
    object Versions {
        const val ktx = "1.7.0"
        const val appCompat = "1.4.1"
        const val material = "1.5.0"
        const val hilt = BuildPlugins.Versions.hilt
        const val navigation = BuildPlugins.Versions.navigation
        const val constraintLayout = "2.1.3"
        const val paging = "3.1.0"
        const val apacheCommonCsv = "1.9.0"
    }

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationDynamicFeatures =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val apacheCommonCsv = "org.apache.commons:commons-csv:${Versions.apacheCommonCsv}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val espressoCore = "3.4.0"
        const val espressoIntents = "3.1.0"
        const val espressoContrib = "3.4.0"
        const val testExtensions = "1.1.1"
        const val hilt = BuildPlugins.Versions.hilt
        const val kluent = "1.68"
        const val testRunner = "1.1.0"
        const val testRules = "1.1.0"
        const val robolectric = "4.4"
        const val mockk = "1.12.2"
        const val kotlin = Kotlin.coroutines
        const val fragmentTesting = "1.4.0"
        const val navigationTesting = Libraries.Versions.navigation
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoContrib}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigationTesting}"
}

object DevLibraries {
    private object Versions {
        const val leakCanary = "2.8.1"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}