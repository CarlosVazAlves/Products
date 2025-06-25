plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
    namespace = "com.carlosalves.products"
    compileSdk = 36

    buildFeatures {
        compose = true
        buildConfig = true
    }

    flavorDimensions += "env"

    productFlavors {
        create("form") {
            dimension = "env"
            buildConfigField("Boolean", "FORM_MODE_ENABLED", "true")
        }
        create("list") {
            dimension = "env"
            buildConfigField("Boolean", "FORM_MODE_ENABLED", "false")
        }
    }

    defaultConfig {
        applicationId = "com.carlosalves.Products"
        minSdk = 32
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.kotlinx.coroutines.android)

    //Hilt
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.work.runtime)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Ok Http
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    implementation (libs.compose.charts)

    implementation(libs.coil.compose)
}