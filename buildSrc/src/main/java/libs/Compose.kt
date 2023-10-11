package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Compose {

    private val dependencies = listOf(
        "androidx.compose.ui:ui:$COMPOSE_VERSION",
        "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION",
        "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION",
        "androidx.compose.ui:ui-test-manifest:$COMPOSE_VERSION",
        "androidx.compose.material3:material3:$MATERIAL",
        "androidx.navigation:navigation-compose:$NAV_VERSION",
        "androidx.constraintlayout:constraintlayout-compose:$CONSTRAINT_COMPOSE_VERSION",
        "androidx.compose.foundation:foundation:$COMPOSE_STAGGERED_GRID",
    )

    fun DependencyHandler.compose(cofigurationName: String = "implementation") {
        dependencies.forEach { add(cofigurationName, it) }
    }
}
