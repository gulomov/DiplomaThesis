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
            excludes += "META-INF/gradle/incremental.annotation.processors"
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

    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.auth)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.timber)

}

kapt {
    correctErrorTypes = true
}
