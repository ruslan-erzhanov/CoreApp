package com.erzhanov.coreapp.data.auth.di

import com.erzhanov.coreapp.data.auth.repos.AuthRepository
import com.erzhanov.coreapp.data.auth.repos.AuthRepositoryImpl
import com.erzhanov.coreapp.data.auth.repos.TokenRepository
import com.erzhanov.coreapp.data.auth.repos.TokenRepositoryImpl
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        tokenPreferences: PrefsDataSource,
        httpClient: HttpClient,
    ): AuthRepository {
        return AuthRepositoryImpl(
            tokenPreferences, httpClient
        )
    }

    @Provides
    @Singleton
    fun provideTokenRepository(tokenPreferences: PrefsDataSource): TokenRepository {
        return TokenRepositoryImpl(tokenPreferences)
    }
}