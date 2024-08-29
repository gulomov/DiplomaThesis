plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.service)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.diploma.work.diplomathesis"

    defaultConfig {
        applicationId = "com.diploma.work.diplomathesis"
        minSdk = 25
        compileSdk = 35
        targetSdk = 35
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

    detekt {
        toolVersion = "1.23.3"
        config.setFrom(file("config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }
}

dependencies {

    implementation(project(":features:splash"))
    implementation(project(":features:introduction"))
    implementation(project(":features:home"))
    implementation(project(":features:productdetail"))
    implementation(project(":features:favorites"))
    implementation(project(":features:gallery"))
    implementation(project(":features:search"))
    implementation(project(":core:database"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))

    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
    implementation(libs.core.ktx)
    implementation(libs.activity.ktx)
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.auth)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.timber)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)

}

kapt {
    correctErrorTypes = true
}
