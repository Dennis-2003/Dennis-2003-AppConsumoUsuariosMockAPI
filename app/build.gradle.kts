plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tuapp.usuariosapi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tuapp.usuariosapi"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    // Habilita Jetpack Compose en el proyecto
    buildFeatures {
        compose = true
    }

    composeOptions {
        // Versión del compilador de Compose compatible con Kotlin 1.9.x
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Jetpack Compose BOM — sincroniza versiones de todas las libs de Compose
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Material Components for Android - Necessary for Theme.Material3.* XML themes
    implementation("com.google.android.material:material:1.12.0")

    // Retrofit — cliente HTTP para consumir la API REST
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson Converter — convierte JSON de la API en data classes Kotlin
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coroutines Android — ejecución asíncrona sin bloquear la UI
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Coil — carga de imágenes desde URL en Compose (avatares)
    implementation("io.coil-kt:coil-compose:2.6.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
