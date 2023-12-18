plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    linuxX64("linux") {
        binaries {
            executable{
                entryPoint = "main"
            }
        }
    }

    mingwX64("mingw") {
        binaries {
            executable()
        }
    }

    macosX64("macos") {

    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.tercad.zabauka"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
