package com.erzhanov.coreapp.core.network

import com.erzhanov.coreapp.core.network.ApiContract.Headers
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultHeadersInterceptor @Inject constructor(
    private val prefsDataSource: PrefsDataSource,
    private val isDebug: Boolean,
    private val apiKey: String
) : HeadersInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //Current api doesn't require any of headers, just mocking this flow
        val requestBuilder = runBlocking {
            chain.request().newBuilder().apply {

                //todo:remove mocking code
                if (isDebug) return@apply

                header(Headers.PLATFORM, "android")
                header(Headers.UUID, prefsDataSource.getDeviceUUID())
                header(Headers.DEBUG, isDebug.toString())
                header(Headers.VERSION, "1")
                if (apiKey.isNotEmpty()) header(Headers.API_KEY, apiKey)
                prefsDataSource.getUserToken().takeUnless { it.isNullOrEmpty() }?.let { token ->
                    header(Headers.AUTHORIZATION, "${Headers.BEARER}$token")
                }
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}