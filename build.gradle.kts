buildscript {
    extra.apply {
        set("compose_ui_version", "1.2.0")
        set("gms_version", "4.3.14")
    }

    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
        classpath("com.android.tools.build:gradle:8.4.0")
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.service) apply false
    alias(libs.plugins.ksp) apply false
}
