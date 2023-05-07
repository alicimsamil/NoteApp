// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(ClassPaths.kotlinGradlePath) version Versions.kotlinGradleVersion apply false
    id(ClassPaths.androidApplicationPath) version Versions.androidApplicationPathVersion apply false
    id(ClassPaths.androidLibraryPath) version Versions.androidLibraryPathVersion apply false
    id(ClassPaths.hiltPath) version Versions.hiltLibraryVersion apply false
    id(ClassPaths.navigationPath) version Versions.navVersion apply false
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}