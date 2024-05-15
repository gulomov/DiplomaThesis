package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Ktor {
    private val dependencies = listOf(
        "io.ktor:ktor-server-core-jvm:$KTOR",
        "io.ktor:ktor-server-netty-jvm:$KTOR",
        "io.ktor:ktor-server-call-logging:$KTOR",
        "io.ktor:ktor-serialization-kotlinx-json:$KTOR",
        "ch.qos.logback:logback-classic:$LOG_BACK"
    )

    fun DependencyHandler.ktor(configurationName: String = "implementation") {
        dependencies.forEach { add(configurationName, it) }
    }
}