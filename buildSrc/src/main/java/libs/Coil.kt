package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Coil {

    fun DependencyHandler.coil(configurationName: String = "implementation") {
        add(configurationName, "io.coil-kt:coil-compose:$COIL_COMPOSE")
    }
}
