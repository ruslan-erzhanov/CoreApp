plugins {
    `android-library-build`
}

android {
    namespace = "com.erzhanov.coreapp.reddits"
}

dependencies {

    implementation(project(Modules.coreBase))
    implementation(project(Modules.dataCommon))
    implementation(project(Modules.dataReddits))
    implementation(Coil.coil)
}