package libs

import org.gradle.api.artifacts.dsl.DependencyHandler

object AccompanistPager {

    fun DependencyHandler.accompanistPager(configurationName: String = "implementation") {
        add(configurationName, "com.google.accompanist:accompanist-pager:$ACCOMPANIST_PAGER")
    }
}
