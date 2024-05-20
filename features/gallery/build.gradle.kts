plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.diploma.work.gallery"

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
    implementation(project(":core:navigation"))
    implementation(project(":features:common"))

    implementation(libs.core.ktx)
    implementation(libs.livedata.ktx)
    implementation(libs.viewmodel.ktx)
    implementation(libs.runtime.ktx)
    implementation(libs.webkit)
    implementation(libs.accompanist.pager)
    implementation(libs.coil.compose)
    implementation(libs.ui.comopose)
    implementation(libs.tooling)
    implementation(libs.tooling.preview)
    implementation(libs.tooling.manifest)
    implementation(libs.material)
    implementation(libs.navigation.compose)
    implementation(libs.constraint.compose)
    implementation(libs.foundation)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.timber)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}
