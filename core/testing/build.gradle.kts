import libs.AndroidCode.androidCore
import libs.Compose.compose
import libs.UnitTest.androidTestImplementations
import libs.UnitTest.testImplementations

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.diploma.work.testing"

    defaultConfig {
        minSdk = 24
        compileSdk = 34
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
    implementation(project(":core:repository"))

    androidCore()
    compose()
    testImplementations()
    androidTestImplementations()
}