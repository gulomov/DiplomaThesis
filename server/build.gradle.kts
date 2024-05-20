
plugins {
    alias(libs.plugins.ktor)
    application
}
group = "com.diploma.work.server"
version = "0.0.1"

application {
    mainClass.set("com.diploma.work.server.ApplicationKt")
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization)
    implementation(libs.logback)
}