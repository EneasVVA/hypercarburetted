plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.androidHilt)
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

    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
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

    // For instrumentation tests
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.hilt)

    // For local unit tests
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.hilt)



}