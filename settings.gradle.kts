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
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
rootProject.name = "CoreApp"
include("app")
include("app:_auth")
include("app:_reddits")

include("core")
include("core:network")
include("core:base")

include("data")
include("data:common")
include("data:auth")
include("data:storage")
include("data:reddits")