import libs.Hilt.hilt
import libs.Room.room

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.deploma.work.data"

    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
    }

    dependencies {
        room()
        hilt()
    }
}
