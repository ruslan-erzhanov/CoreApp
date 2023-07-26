plugins {
    id("com.android.library")
    `kotlin-android`
    `kotlin-kapt`
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.kotlinCompilerVersion
    }
    packaging {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies{

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleVmKtx)


    implementation(platform(Compose.platformBom))
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.graphics)
    implementation(Compose.toolingPreview)
    implementation(Compose.material3)
    implementation(Compose.navigation)

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    debugImplementation(Compose.tooling)
}