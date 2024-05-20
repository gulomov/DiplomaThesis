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
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.diploma.work.database"
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
    implementation(libs.core.ktx)
    implementation(libs.livedata.ktx)
    implementation(libs.viewmodel.ktx)
    implementation(libs.runtime.ktx)
    implementation(libs.webkit)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    implementation(libs.hilt.compiler)
    implementation(libs.navigation.compose)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.timber)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    kapt(libs.room.compiler)
}

kapt {
    correctErrorTypes = true
}