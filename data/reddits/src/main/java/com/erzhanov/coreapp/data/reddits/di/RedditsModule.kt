package com.erzhanov.coreapp.data.reddits.di

import com.erzhanov.coreapp.data.reddits.repos.RedditsRepository
import com.erzhanov.coreapp.data.reddits.repos.RedditsRepositoryImpl
import com.erzhanov.coreapp.data.storage.db.RedditsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RedditsModule {

    @Provides
    @Singleton
    fun provideRedditsRepository(
        redditsDao: RedditsDao,
        httpClient: HttpClient,
    ): RedditsRepository {
        return RedditsRepositoryImpl(redditsDao, httpClient)
    }
}