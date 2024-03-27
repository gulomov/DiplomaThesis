pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DiplomaThesis"
include(":app")
include(":features")
include(":features:splash")
include(":features:introduction")
include(":features:home")
include(":core")
include(":core:database")
include(":core:sharedpreference")
include(":core:repository")
include(":core:composables")
