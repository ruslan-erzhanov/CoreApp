plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Note: we can't use here `implementation(Libraries.gradlePlugin)` and hence
    // after upgrading version also should be changed in `.build.gradle.kts`
    val kotlinVersion = "1.8.22"
    val androidPluginVersion = "8.0.2"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    val androidGP = "com.android.tools.build:gradle:$androidPluginVersion"
    implementation(androidGP)
    implementation(kotlinGradlePlugin)
    // to fix 'java.lang.String com.squareup.javapoet.ClassName.canonicalName()'
    // https://github.com/google/dagger/issues/3068
    implementation("com.squareup:javapoet:1.13.0")
}