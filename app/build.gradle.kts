plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.androidHilt)
    id(BuildPlugins.safeArgsPlugin)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        applicationId = AndroidClient.appId
        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName
        testInstrumentationRunner = AndroidClient.testRunner
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    testOptions {
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true // workaround to make compatible android gradle plugin and mockk-android
            }
        }
    }
}

dependencies {
    //Compile time dependencies
    kapt(Libraries.hiltCompiler)

    // Application dependencies
    implementation(Libraries.ktxCore)
    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.hilt)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationDynamicFeatures)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.paging)
    implementation(Libraries.apacheCommonCsv)

    // For instrumentation tests
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.espressoIntents)
    androidTestImplementation(TestLibraries.espressoContrib)
    androidTestImplementation(TestLibraries.hilt)
    androidTestImplementation(TestLibraries.testRules)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.navigationTesting)

    // For local unit tests
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
    testImplementation(TestLibraries.robolectric)
    testImplementation(TestLibraries.coroutines)

    // For debugging
    debugImplementation(DevLibraries.leakCanary)
    debugImplementation(TestLibraries.fragmentTesting)

}