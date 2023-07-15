package com.erzhanov.coreapp.core.network

import android.os.Build
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultHeadersInterceptor @Inject constructor(
    private val prefsDataSource: PrefsDataSource,
    private val isDebug: Boolean,
    private val apiKey: String
) : HeadersInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = runBlocking {

            chain.request().newBuilder().apply {
                //header("client-version", BuildConfig.VERSION_NAME)
                //header("platform", "android")
                //header("uuid", prefsDataSource.getDeviceUUID())
                header("debug", isDebug.toString())
                header("version", "1")
                header("x-api-key", apiKey)
                prefsDataSource.getUserToken()?.let { token ->
                    header("token", token)
                }
            }
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun getDeviceInfo(): String {
        val manufacturer: String = Build.MANUFACTURER
        val model: String = Build.MODEL
        val locale = Locale.getDefault()
        val modelInfo = if (model.lowercase(locale).startsWith(manufacturer.lowercase(locale))) {
            model
        } else {
            "$manufacturer $model"
        }.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(locale) else it.toString()
        }

        return "$modelInfo (Android ${Build.VERSION.RELEASE})"
    }
}