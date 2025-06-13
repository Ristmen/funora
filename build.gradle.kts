plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()
    ios()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
                implementation("io.insert-koin:koin-core:3.4.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.firebase:firebase-auth-ktx:21.2.0")
            }
        }
        val iosMain by getting
        val jsMain by getting
    }
}

android {
    namespace = "com.pb.funora"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
