plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
        val iosMain by getting
    }
}

