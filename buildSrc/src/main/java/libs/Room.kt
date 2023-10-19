package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Room {

    private val dependency = listOf(
        "androidx.room:room-runtime:$ROOM_VERSION",
        "androidx.room:room-ktx:$ROOM_VERSION",
    )

    fun DependencyHandler.room(configurationName: String = "implementation") {
        dependency.forEach { add(configurationName, it) }
        add("annotationProcessor", "androidx.room:room-compiler:$ROOM_VERSION")
        add("kapt", "androidx.room:room-compiler:$ROOM_VERSION")
    }
}
