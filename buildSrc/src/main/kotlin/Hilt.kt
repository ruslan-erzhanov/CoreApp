object Hilt {
    const val hiltVersion = "2.44"
    const val plagin = "com.google.dagger.hilt.android"
    const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${hiltVersion}"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
}

object HiltTest {
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
}