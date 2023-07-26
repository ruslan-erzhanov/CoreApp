package com.erzhanov.coreapp.data.storage.db

import androidx.room.*
import com.erzhanov.coreapp.data.storage.model.EntityReddit
import kotlinx.coroutines.flow.Flow

@Dao
interface RedditsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReddit(entityReddit: EntityReddit)

    @Query("SELECT * FROM SavedReddit")
    fun getAllReddits(): Flow<List<EntityReddit>>

    @Query("SELECT * FROM SavedReddit WHERE id = :id")
    suspend fun getReddit(id: String): EntityReddit

    @Query("SELECT * FROM SavedReddit WHERE id IN (:ids)")
    suspend fun getReddits(ids: List<String>): List<EntityReddit>

    @Query("DELETE FROM SavedReddit WHERE id = :id")
    suspend fun deleteReddit(id: String)

    @Query("DELETE FROM SavedReddit")
    suspend fun deleteAll()
}