package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Detekt {
    fun DependencyHandler.detekt(configurationName: String = "detektPlugins") {
        add(configurationName, "ru.kode:detekt-rules-compose:$DETEKT_VERSION")
    }
}
