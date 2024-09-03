plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.diploma.work.repository"

    defaultConfig {
        minSdk = 25
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
}

dependencies {
    implementation(project(":core:sharedpreference"))
    implementation(project(":core:database"))

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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.auth)
    ksp(libs.hilt.android.compiler)
    ksp(libs.room.compiler)
}