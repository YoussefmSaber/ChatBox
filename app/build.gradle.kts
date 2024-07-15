plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.pixe.chatapp"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.pixe.chatapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val VERSION = "2.5.2"

    // Navigation
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    // Material 3
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3)

    //Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Test and Debug
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // ViewModel and lifeCycle
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Glide Image Processing
    implementation(libs.compose)

    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)

    // Constraint Layout
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.constraintlayout.core)

    // Feather Icons
    implementation("br.com.devsrsouza.compose.icons:feather:1.1.0")
    implementation("br.com.devsrsouza.compose.icons:eva-icons:1.1.0")


    // Supabase Core Dependencies for Compose
    implementation(platform("io.github.jan-tennert.supabase:bom:$VERSION"))

        // Supabase Moshi
    implementation("io.github.jan-tennert.supabase:serializer-moshi:$VERSION")

        // Supabase Auth
    implementation("io.github.jan-tennert.supabase:compose-auth:2.5.2")
    implementation("io.github.jan-tennert.supabase:compose-auth-ui:2.5.2")
        // Supabase Storage
    implementation("io.github.jan-tennert.supabase:storage-kt:2.5.2")

        // Supabase RealTime
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.5.2")
    implementation(libs.realtime.kt)

        // Supabase GoTrue
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.2")
    implementation(libs.gotrue.kt)

        // Supabase Ktor
    implementation ("io.ktor:ktor-client-cio:2.3.5")
    implementation ("io.ktor:ktor-client-android:2.3.5")
    implementation(libs.ktor.client.core)

        // Supabase Postgrest
    implementation(libs.postgrest.kt)

}