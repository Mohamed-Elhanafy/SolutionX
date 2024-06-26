plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.plugin.parcelize")

}

android {
    namespace = "com.example.solutionx"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.solutionx"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


    flavorDimensions += listOf("logging")
    productFlavors {
        create("logCat") {
            dimension = "logging"
        }
        create("logWriter") {
            dimension = "logging"
        }
        create("production") {
            dimension = "logging"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }



}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.hilt.common)
    testImplementation(libs.junit)
    testImplementation("org.testng:testng:6.9.6")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.mockito.kotlin)


    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    ksp("androidx.hilt:hilt-compiler:1.2.0")

    //lifeCycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.extensions)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation( libs.kotlinx.coroutines.test)
    androidTestImplementation( libs.kotlinx.coroutines.test)

    //ktor client
    implementation(libs.ktor.client.okhttp)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //okhttp interceptor
    implementation(libs.okhttp.logging.interceptor)

    //dataStore
    implementation(libs.datastore)

    //workManager
    implementation(libs.androidx.work.runtime.ktx)
    androidTestImplementation(libs.androidx.work.testing)
    implementation(libs.androidx.hilt.work)
    implementation(kotlin("reflect"))


}