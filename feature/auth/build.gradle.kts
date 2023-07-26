plugins {
    `android-feature-build`
}

android {
    namespace = "com.erzhanov.coreapp.auth"
}

dependencies {

    implementation(project(Modules.coreBase))
    implementation(project(Modules.dataCommon))
    implementation(project(Modules.dataAuth))
}