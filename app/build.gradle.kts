plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.todoAppRemake"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.todoAppRemake"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

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
    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with Scalar Converter
    implementation(libs.converter.scalars)
    // Gson
//    implementation(libs.gson)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    // navigationCompose
    implementation(libs.androidx.navigation.compose)
    // hiltNavigationCompose
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.converter.gson)

    // MockK
    testImplementation("io.mockk:mockk:1.12.0") // 最新バージョンは適宜確認

    // Kotlin Coroutines Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") // 最新バージョンは適宜確認
}
secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"
}
kapt {
    correctErrorTypes = true
}
