plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.diploma.work.search"

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
}

dependencies {

    implementation(project(":core:design"))
    implementation(project(":core:database"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))
    implementation(project(":features:common"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.viewmodel.ktx)
    implementation(libs.runtime.ktx)
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
    implementation(libs.ui.tooling.preview.android)
    implementation(libs.google.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    ksp(libs.hilt.android.compiler)
}