plugins {
    `android-library-build`
    kotlin(Build.serializationPlugin) version Build.kotlinVersion
}

android {
    namespace = "com.erzhanov.coreapp.data.storage"
    defaultConfig {
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
}

dependencies {
    implementation(project(Modules.dataCommon))
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)
}