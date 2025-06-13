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

rootProject.name = "Funora"
include(":app")
include(":core")
include(":features:auth")
include(":features:profile")
include(":features:home")
include(":features:games")
include(":features:matchmaking")
include(":features:currency")
include(":features:notifications")
include(":features:settings")
