package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Timber {

    fun DependencyHandler.timber(configurationName: String = "implementation") {
        add(configurationName, "com.jakewharton.timber:timber:$TIMBER_VERSION")
    }
}
