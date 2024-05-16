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
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.diploma.work.diplomathesis"

    defaultConfig {
        applicationId = "com.diploma.work.diplomathesis"
        minSdk = DefaultConfig.minSdkVersion
        compileSdk = DefaultConfig.compileSdkVersion
        targetSdk = DefaultConfig.targetSdkVersion
        versionCode = 1
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore/DT.keystore")
            storePassword = "Jalol9050"
            keyPassword = "Jalol9050"
            keyAlias = "key0"
        }

        getByName("debug") {
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":features:splash"))
    implementation(project(":features:introduction"))
    implementation(project(":features:home"))
    implementation(project(":features:productdetail"))
    implementation(project(":features:favorites"))
    implementation(project(":features:gallery"))
    implementation(project(":core:database"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))
    implementation(project(":kmpsharedmodule"))

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

kapt {
    correctErrorTypes = true
}
