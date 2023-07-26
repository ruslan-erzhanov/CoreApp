object Android {
    const val appId = "com.erzhanov.coreapp"
    const val compileSdk = 33
    const val buildTools = "30.0.3"
    const val minSdk = 26
    const val targetSdk = 32
    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 1
    const val versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
    const val versionName = "${versionMajor}.${versionMinor}.${versionPatch}($versionBuild)"
}
//     Major version ⌄       ⌄ Build version
//                  v1.3.4 (123)
//       Minor version ⌃|⌃ Patch version