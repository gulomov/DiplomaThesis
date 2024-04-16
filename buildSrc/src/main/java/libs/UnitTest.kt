package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object UnitTest {
    private val testDependencies = listOf(
        "junit:junit:$JUNIT",
        "org.mockito:mockito-core:$MOCKITO_CORE",
        "org.mockito:mockito-inline:$MOCKITO_CORE",
        "org.mockito.kotlin:mockito-kotlin:$MOCKITO_KOTlIN",
        "org.robolectric:robolectric:$ROBOLECTRIC",
        "com.google.truth:truth:$GOOGLE_TRUTH",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_TEST",
        "com.google.firebase:firebase-database:$FIREBASE_TEST"

    )

    private val androidTestDependencies = listOf(
        "androidx.test.ext:junit-ktx:$JUNIT_KTX",
        "androidx.test.espresso:espresso-core:$ESPRESSO_CORE",
    )

    private val kaptAndroidTest = "androidx.hilt:hilt-compiler:$HILT_COMPILER"

    fun DependencyHandler.testImplementations(configurationName: String = "testImplementation") =
        testDependencies.forEach { add(configurationName, it) }

    fun DependencyHandler.androidTestImplementations(
        configurationName: String = "androidTestImplementation"
    ) = androidTestDependencies.forEach { add(configurationName, it) }
}