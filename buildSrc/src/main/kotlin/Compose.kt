object Compose {
    private const val activityComposeVersion = "1.7.2"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val constraintLayoutVersion = "1.0.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:$constraintLayoutVersion"

    const val kotlinCompilerVersion = "1.4.8"

    private const val composeBomVersion = "2023.06.01"
    const val platformBom = "androidx.compose:compose-bom:$composeBomVersion"
    const val ui = "androidx.compose.ui:ui"
    const val animation = "androidx.compose.animation:animation"
    const val graphics = "androidx.compose.ui:ui-graphics"
    const val tooling = "androidx.compose.ui:ui-tooling"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val material = "androidx.compose.material:material"
    const val material3 = "androidx.compose.material3:material3"

    private const val navigationVersion = "2.6.0"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}

object ComposeTest {
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
}