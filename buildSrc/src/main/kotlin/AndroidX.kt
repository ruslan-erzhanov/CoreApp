object AndroidX {
    private const val coreKtxVersion = "1.10.1"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.6.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val lifecycleKtxVersion = "2.6.1"
    const val lifecycleRntKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtxVersion"
    const val lifecycleVmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleKtxVersion"

    private const val datastoreVersion = "1.0.0"
    const val datastore = "androidx.datastore:datastore-preferences:$datastoreVersion"
}

object AndroidXTest {
    private const val version = "1.5.2"
    const val runner = "androidx.test:runner:$version"
}