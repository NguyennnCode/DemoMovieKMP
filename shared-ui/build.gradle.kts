plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                // Needed only for preview.
                implementation(compose.preview)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.8.0")
            }
        }
        val androidUnitTest by getting

        val desktopMain by getting {
            dependsOn(commonMain)
        }
    }
}

android {
    namespace = "com.example.shared_ui"
    compileSdk = 33
    defaultConfig {
        minSdk = 29
        targetSdk = 33
    }
}