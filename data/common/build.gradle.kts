plugins {
    `library-build`
    kotlin(KotlinPlugins.serialization) version Build.kotlinVersion
}

dependencies {
    implementation(Ktor.core)
    implementation(Ktor.clientSerialization)
}