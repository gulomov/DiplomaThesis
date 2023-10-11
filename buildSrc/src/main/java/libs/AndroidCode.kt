package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object AndroidCode {
    private val dependencies = listOf(
        "androidx.core:core-ktx:$CORE_KTX",
        "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_KTX",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_KTX",
        "androidx.webkit:webkit:$WEBKIT_VERSION",
        "androidx.lifecycle:lifecycle-runtime-ktx:$RUNTIME_KTX",
    )

    fun DependencyHandler.androidCore(configurationName: String = "implementation") =
        dependencies.forEach { add(configurationName, it) }
}
