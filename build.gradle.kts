buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidGP)
        classpath(Build.kotlinGradlePlugin)
        classpath(Hilt.gradlePlugin)
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}