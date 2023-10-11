package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Moshi {

    fun DependencyHandler.moshi(configurationName: String = "implementation") {
        add(configurationName, "com.squareup.moshi:moshi:$MOSHI_VERSION")
    }
}
