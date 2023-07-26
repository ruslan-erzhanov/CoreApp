plugins {
    `android-library-build`
    kotlin(Build.serializationPlugin) version Build.kotlinVersion
}

android {
    namespace = "com.erzhanov.coreapp.data.auth"
}

dependencies {
    implementation(project(Modules.dataCommon))
    implementation(project(Modules.coreNetwork))
}