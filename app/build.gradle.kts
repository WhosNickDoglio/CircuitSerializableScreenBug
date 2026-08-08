plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcel)
    alias(libs.plugins.metro)
}

android {
    namespace = "dev.whosnickdoglio.circuit"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "dev.whosnickdoglio.circuit"
        minSdk = 30
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.kotlin.serialization)
    implementation(libs.circuit.foundation)
    implementation(libs.circuit.codegen.annotations)
    implementation(libs.circuit.serialization)
    implementation(libs.metrox.android)
}
