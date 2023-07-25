plugins {
    //trick: for the same plugin versions in all sub-modules
    id(Plugins.androidApp).version(Versions.Plugins.android).apply(false)
    id(Plugins.androidLib).version(Versions.Plugins.android).apply(false)
    kotlin(Plugins.kotlinAndroid).version(Versions.Plugins.kotlin).apply(false)
    kotlin(Plugins.kotlinMultiplatform).version(Versions.Plugins.kotlin).apply(false)
    kotlin(Plugins.serialization).version(Versions.Plugins.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
