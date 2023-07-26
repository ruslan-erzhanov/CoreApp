plugins {
    `android-feature-build`
}

android {
    namespace = "com.erzhanov.coreapp.core.base"
}

dependencies {

    api(Accompanist.systemui)
    api(UI.shimmer)
}