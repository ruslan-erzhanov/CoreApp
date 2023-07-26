plugins {
    id(Build.androidApp)
    kotlin("kapt")
    kotlin("android")
    id(Hilt.plagin)
    kotlin(Build.serializationPlugin) version Build.kotlinVersion
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

        debug {
            isMinifyEnabled  = false
            buildConfigField(ConfigVariables.Names.baseHost, ConfigVariables.Debug.baseHost)
            buildConfigField(ConfigVariables.Names.apiKey, ConfigVariables.Debug.apiKey)
        }
        release {
            buildConfigField(ConfigVariables.Names.baseHost, ConfigVariables.Release.baseHost)
            buildConfigField(ConfigVariables.Names.apiKey, ConfigVariables.Release.apiKey)
            isMinifyEnabled  = true
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

    implementation(project(Modules.dataCommon))
    implementation(project(Modules.coreBase))
    implementation(project(Modules.featureAuth))
    implementation(project(Modules.featureReddits))

    implementation(platform(Kotlin.BOM))
    implementation(AndroidX.coreKtx)

    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.datastore)

    implementation(platform(Compose.platformBom))
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.graphics)
    implementation(Compose.toolingPreview)
    implementation(Compose.material3)
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)

    implementation(Coil.coil)

    implementation(Accompanist.animations)
    implementation(Accompanist.material)

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