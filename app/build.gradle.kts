plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.hilt)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.route"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.route"
        minSdk = 24
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.recyclerview)

    // Android LifeCycle
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)

    // ViewModel
    implementation(libs.androidx.lifecycle.livedata.ktx.v262)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)

    // Kotlin Reflect
    implementation(libs.kotlin.reflect)

    // SDP && SSP
    implementation(libs.intuit.sdp)
    implementation(libs.intuit.ssp)

    // GSON
    implementation(libs.google.gson)

    //Glide
    implementation(libs.glide)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    kapt(libs.google.hilt.android.compiler)

    // MockK
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.kotlinx.coroutines.test)
}