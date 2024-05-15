import libs.Ktor.ktor

plugins {
    id("io.ktor.plugin") version "2.3.11"
    id("org.jetbrains.kotlin.jvm")
    application
}
group = "com.diploma.work.server"
version = "0.0.1"

application {
    mainClass.set("com.diploma.work.server.ApplicationKt")
}

dependencies {
    ktor()
}