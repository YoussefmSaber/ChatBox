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
    implementation(libs.androidx.constraintlayout.core)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    val VERSION = "2.5.2"
    val ktor_version = "2.3.5"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation(platform("io.github.jan-tennert.supabase:bom:$VERSION"))
    implementation("io.github.jan-tennert.supabase:serializer-moshi:$ktor_version")
    implementation("io.github.jan-tennert.supabase:compose-auth:2.5.2")
    implementation("io.github.jan-tennert.supabase:compose-auth-ui:2.5.2")
    implementation("io.github.jan-tennert.supabase:storage-kt:2.5.2")
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.5.2")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.2")
    implementation ("io.ktor:ktor-client-cio:$ktor_version")

    implementation ("io.ktor:ktor-client-android:$ktor_version")

    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.postgrest.kt)
    implementation(libs.gotrue.kt)
    implementation(libs.realtime.kt)
    implementation(libs.ktor.client.core)
    implementation(libs.androidx.constraintlayout.compose)
}