plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.diploma.work.comingsoon"

    defaultConfig {
        compileSdk = 35
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

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.google.material)
    implementation(libs.ui.comopose)
    implementation(libs.tooling)
    implementation(libs.tooling.preview)
    implementation(libs.tooling.manifest)
    implementation(libs.material)
    implementation(libs.navigation.compose)
    implementation(libs.constraint.compose)
    implementation(libs.foundation)
    implementation(libs.ui.tooling.preview.android)
}