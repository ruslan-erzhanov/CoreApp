plugins {
    id(Build.androidApp)
    kotlin("kapt")
    kotlin("android")
    id(Hilt.plagin)
}

android {
    namespace = Android.appId
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.compileSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled  = false
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.kotlinCompilerVersion
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(platform(Kotlin.BOM))
    implementation(AndroidX.lifecycleVmKtx)
    implementation(platform(Compose.platformBom))
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.graphics)
    implementation(Compose.toolingPreview)
    implementation(Compose.material3)
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    androidTestImplementation(platform(Compose.platformBom))
    debugImplementation(Compose.tooling)
    androidTestImplementation(AndroidXTest.runner)
    androidTestImplementation(ComposeTest.uiTestJunit4)
    debugImplementation(ComposeTest.uiTestManifest)
    androidTestImplementation(HiltTest.hiltAndroidTesting)
    kaptAndroidTest(Hilt.compiler)
    androidTestImplementation(Junit.junit4)
}