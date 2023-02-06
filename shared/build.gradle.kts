plugins {
    id(Plugins.androidLib)
    kotlin(Plugins.kotlinMultiplatform)
    kotlin(Plugins.serialization) version Versions.Plugins.serialization
}

kotlin {
    android()

    ios {
        binaries {
            framework("Shared") {
            }
        }
    }

    sourceSets {

        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
            }
        }

        val commonMain by getting {
            dependencies {
                Dependencies.Shared.commonMain.forEach {
                    implementation(it)
                }
                Dependencies.Shared.commonKotlin.forEach {
                    implementation(kotlin(it))
                }
            }
        }
        val commonTest by getting {
            dependencies {
                Dependencies.Shared.commonTest.forEach {
                    implementation(it)
                }
                Dependencies.Shared.commonKotlinTest.forEach {
                    implementation(kotlin(it))
                }
            }
        }
        val androidMain by getting {
            dependencies {
                Dependencies.Shared.androidMain.forEach {
                    implementation(it)
                }
            }
        }
        val androidTest by getting {
            dependencies {
                Dependencies.Shared.androidKotlinTest.forEach {
                    implementation(kotlin(it))
                }
                Dependencies.Shared.androidTest.forEach {
                    implementation(it)
                }
            }
        }
        val iosMain by getting {
            dependencies {
                Dependencies.Shared.iosMain.forEach {
                    implementation(it)
                }
            }
        }
        val iosTest by getting
    }
}

android {
    namespace = "dev.eduayuso.kmcs"
    compileSdk = Versions.AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.AndroidSdk.min
        targetSdk = Versions.AndroidSdk.target
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}