plugins {
    `library-build`
    kotlin(Build.serializationPlugin) version Build.kotlinVersion
}

dependencies {
    api(Ktor.core)
    api(Ktor.clientSerialization)
}