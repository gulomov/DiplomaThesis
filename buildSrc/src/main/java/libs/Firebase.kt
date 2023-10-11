package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object Firebase {

    private val dependencies = listOf(
        "com.google.firebase:firebase-analytics-ktx",
        "com.google.firebase:firebase-database-ktx",
        "com.google.firebase:firebase-firestore-ktx",
        "com.google.firebase:firebase-messaging-ktx",
        "com.google.firebase:firebase-auth-ktx:$FIREBASE_AUTH",
    )

    fun DependencyHandler.firebase(configurationName: String = "implementation") {
        add(configurationName, platform("com.google.firebase:firebase-bom:$FIREBASE_BOM"))
        dependencies.forEach { add(configurationName, it) }
    }
}
