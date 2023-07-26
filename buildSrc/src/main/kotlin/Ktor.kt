object Ktor {
    private const val ktorVersion = "2.3.2"
    const val core = "io.ktor:ktor-client-core:$ktorVersion"
    const val okhttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
    const val negotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val clientSerialization = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val logging = "io.ktor:ktor-client-logging:$ktorVersion"
    const val android = "io.ktor:ktor-client-android:$ktorVersion"

    const val ktorClientMock = "io.ktor:ktor-client-mock:$ktorVersion"
    const val externalLogging = "com.github.ihsanbal:LoggingInterceptor:3.1.0"
}