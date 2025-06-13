plugins {
    kotlin("multiplatform") version "1.9.0"
    id("com.android.library")
    kotlin("plugin.serialization") version "1.9.0"
}

kotlin {
    android()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
                implementation("io.insert-koin:koin-core:3.4.0")
            }
        }
        val androidMain by getting
    }
}

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
