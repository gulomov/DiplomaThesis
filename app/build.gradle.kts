import libs.AccompanistPager.accompanistPager
import libs.AndroidCode.androidCore
import libs.Coil.coil
import libs.Compose.compose
import libs.Firebase.firebase
import libs.Hilt.hilt
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
    namespace = "com.deploma.work.diplomathesis"

    defaultConfig {
        applicationId = "com.deploma.work.diplomathesis"
        minSdk = DefaultConfig.minSdkVersion
        compileSdk = DefaultConfig.compileSdkVersion
        targetSdk = DefaultConfig.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
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

    implementation(project(":data"))
    implementation(project(":features"))
    implementation(project(":screens"))

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
