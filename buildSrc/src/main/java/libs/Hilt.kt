package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Hilt {

    private val dependency = listOf(
        "com.google.dagger:hilt-android:$HILT_VERSION",
        "androidx.hilt:hilt-navigation-compose:$HILT_NAVIGATION_COMPOSE_VERSION",
    )

    fun DependencyHandler.hilt(configurationName: String = "implementation") {
        dependency.forEach { add(configurationName, it) }
        add("kapt", "com.google.dagger:hilt-android-compiler:$HILT_VERSION")
        add("kapt", "androidx.hilt:hilt-compiler:$HILT_COMPILER_VERSION")
    }
}
