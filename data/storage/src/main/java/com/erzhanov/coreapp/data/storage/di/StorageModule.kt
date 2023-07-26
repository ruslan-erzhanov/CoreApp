package com.erzhanov.coreapp.data.storage.di

import android.app.Application
import androidx.room.Room
import com.erzhanov.coreapp.data.storage.db.RedditsDao
import com.erzhanov.coreapp.data.storage.db.RedditsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StorageModule {

    @Provides
    @Singleton
    fun provideRedditDatabase(
        app: Application
    ): RedditsDatabase {
        return Room
            .databaseBuilder(app, RedditsDatabase::class.java, "reddit")
            .build()
    }

    @Provides
    @Singleton
    fun provideRedditsDao(
        db: RedditsDatabase
    ): RedditsDao {
        return db.redditDao()
    }
}