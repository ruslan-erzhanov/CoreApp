package com.erzhanov.coreapp.core.network

import com.erzhanov.coreapp.data.common.AppInfoProvider
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        json: Json,
        defaultHeadersInterceptor: HeadersInterceptor,
        infoProvider: AppInfoProvider,
    ): HttpClient {
        return getHttpClient(
            json = json,
            isDebug = infoProvider.isDebug,
            defaultHeadersInterceptor = defaultHeadersInterceptor,
            host = infoProvider.baseHost,
            errorResponseHandler = {}
        )
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        }
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(
        prefsDataSource: PrefsDataSource,
        infoProvider: AppInfoProvider,
    ): HeadersInterceptor {
        return DefaultHeadersInterceptor(
            prefsDataSource,
            isDebug = infoProvider.isDebug,
            apiKey = infoProvider.apiKey,
        )
    }
}