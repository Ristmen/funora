plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.insert-koin:koin-core:3.4.0")
            }
        }
    }
}
