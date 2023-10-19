buildscript {
    extra.apply {
        set("compose_ui_version", "1.2.0")
        set("gms_version", "4.3.14")
    }

    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
        classpath("com.android.tools.build:gradle:8.1.1")
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.gms.google-services") version "4.3.14" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
