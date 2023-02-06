plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
}

android {
    namespace = "dev.eduayuso.kmcs.android"
    compileSdk = Versions.AndroidSdk.compile
    defaultConfig {
        applicationId = AndroidApp.id
        minSdk = Versions.AndroidSdk.min
        targetSdk = Versions.AndroidSdk.target
        versionCode = AndroidApp.versionCode
        versionName = AndroidApp.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {

    implementation(project(":shared"))
    Dependencies.android.forEach {
        implementation(it)
    }
}