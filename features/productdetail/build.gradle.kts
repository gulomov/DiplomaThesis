import libs.AccompanistPager.accompanistPager
import libs.AndroidCode.androidCore
import libs.Coil.coil
import libs.Compose.compose
import libs.Firebase.firebase
import libs.Hilt.hilt
import libs.LogBack.logback
import libs.Moshi.moshi
import libs.Room.room
import libs.Timber.timber

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.diploma.work.prdoductdetail"

    defaultConfig {
        minSdk = 24
        compileSdk = 34
    }

    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
}

dependencies {
    implementation(project(":core:design"))
    implementation(project(":core:database"))
    implementation(project(":core:repository"))
    implementation(project(":features:common"))
    implementation(project(":features:booking"))

    logback()
    androidCore()
    accompanistPager()
    coil()
    compose()
    firebase()
    hilt()
    moshi()
    timber()
    room()
}
