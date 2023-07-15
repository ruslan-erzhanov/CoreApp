package com.erzhanov.coreapp.core.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit

fun getHttpClient(
    json: Json,
    defaultHeadersInterceptor: Interceptor,
    authenticator: Authenticator = Authenticator.NONE,
    additionalInterceptors: List<Interceptor> = emptyList(),
    host: String,
    isDebug: Boolean,
    errorResponseHandler: (ClientRequestException) -> Unit = {}
): HttpClient {

    val httpLoggingInterceptor = LoggingInterceptor.Builder()
        .setLevel(if (isDebug) Level.BASIC else Level.NONE)
        .log(Platform.INFO)
        .tag("NETWORK")
        .build()

    return HttpClient(OkHttp) {

        Logging {
            level = if (isDebug) LogLevel.INFO else LogLevel.NONE
        }
        Json {
            serializer = KotlinxSerializer(json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            this.host = host
            url {
                protocol = URLProtocol.HTTPS
            }
        }

        HttpResponseValidator {
            handleResponseException { exception ->
                val clientException = exception as? ClientRequestException
                    ?: return@handleResponseException
                errorResponseHandler(clientException)
            }
        }

        engine {
            config {
                connectTimeout(60L, TimeUnit.SECONDS)
                readTimeout(60L, TimeUnit.SECONDS)
                writeTimeout(60L, TimeUnit.SECONDS)
                authenticator(authenticator)
            }
            addInterceptor(defaultHeadersInterceptor)
            additionalInterceptors.forEach { interceptor -> addInterceptor(interceptor) }
            addInterceptor(httpLoggingInterceptor)
        }
    }
}