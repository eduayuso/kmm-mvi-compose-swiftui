pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

rootProject.name = "KMCS"
include(":androidApp")
include(":shared")