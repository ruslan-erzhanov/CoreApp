package com.erzhanov.coreapp.di

import android.app.Application
import coil.ImageLoader
import coil.disk.DiskCache
import com.erzhanov.coreapp.BuildConfig
import com.erzhanov.coreapp.data.common.AppInfoProvider
import com.erzhanov.coreapp.data.common.Logger
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import com.erzhanov.coreapp.prefs.PrefsDataSourceImpl
import com.erzhanov.coreapp.util.AppInfoProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return Logger(
            tag = "AppDebug",
            isDebug = BuildConfig.DEBUG
        )
    }

    /**
     * Coil docs say: Coil performs best when you create a single ImageLoader and share it throughout your app. This is because each ImageLoader has its own memory cache, bitmap pool, and network observer.
     * For testing: https://coil-kt.github.io/coil/image_loaders/#testing
     */
    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader {
        return ImageLoader.Builder(app)
            .crossfade(true)
            .diskCache {
                DiskCache.Builder()
                    .directory(app.cacheDir.resolve("image_cache"))
                    .build()
            }
            .build()
    }

    @Provides
    @Singleton
    fun providePrefsDataSource(
        context: Application,
        json: Json
    ): PrefsDataSource {
        return PrefsDataSourceImpl(context, json)
    }

    @Provides
    @Singleton
    fun provideInfoProvider(
        context: Application,
    ): AppInfoProvider {
        return AppInfoProviderImpl(context)
    }
}