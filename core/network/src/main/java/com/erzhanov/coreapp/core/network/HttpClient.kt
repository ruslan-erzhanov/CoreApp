package com.erzhanov.coreapp.core.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
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

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            this.host = host
            url {
                protocol = URLProtocol.HTTPS
            }
        }

        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
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