plugins {
    `android-library-build`
}

android {
    namespace = "com.erzhanov.coreapp.core.network"
}

dependencies {
    implementation(project(Modules.dataCommon))
    implementation(Ktor.okhttp)
    implementation(Ktor.negotiation)
    implementation(Ktor.logging)
    implementation(Ktor.externalLogging) {
        exclude("org.json", "json")
    }
}