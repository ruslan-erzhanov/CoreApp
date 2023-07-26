package com.erzhanov.coreapp.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erzhanov.coreapp.data.storage.model.EntityReddit

@Database(
    entities = [EntityReddit::class,], version = 1)
abstract class RedditsDatabase: RoomDatabase() {
    abstract fun redditDao(): RedditsDao
}