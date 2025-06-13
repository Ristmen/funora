plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.pb.funora.home"
    compileSdk = 34

    defaultConfig { minSdk = 24 }

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.0" }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core"))
    implementation("androidx.compose.material3:material3:1.1.0")
}
