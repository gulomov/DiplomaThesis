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
include(":core:design")
include(":core:navigation")
include(":core:testing")
include(":features:productdetail")
include(":features:common")
include(":features:favorites")
include(":features:gallery")
include(":features:booking")
include(":server")
include(":kmpsharedmodule")
include(":features:search")
include(":features:comingsoon")
