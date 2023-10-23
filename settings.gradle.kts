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
include(":screens")
include(":spash")
include(":features:splash")
include(":features:introduction")
include(":features:home")
include(":base:database")
