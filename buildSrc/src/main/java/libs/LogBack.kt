package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object LogBack {

    private val dependency = listOf(
        "org.slf4j:slf4j-api:$SLF4J",
        "com.github.tony19:logback-android:$LOCK_BACK",
    )

    fun DependencyHandler.logback(configurationName: String = "implementation") {
        dependency.forEach { add(configurationName, it) }
    }
}
