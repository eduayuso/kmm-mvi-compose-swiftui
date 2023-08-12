import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
}

val properties =  Properties()
if (rootProject.file("local.properties").exists()) {
    properties.load(rootProject.file("local.properties").inputStream())
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "DUMMY_API_KEY", properties.getProperty("dummyapi.key"))
        }
        getByName("debug") {
            buildConfigField("String", "DUMMY_API_KEY", properties.getProperty("dummyapi.key"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(project(":shared"))
    Dependencies.androidPlatform.forEach {
        implementation(platform(it))
    }
    Dependencies.android.forEach {
        implementation(it)
    }
}