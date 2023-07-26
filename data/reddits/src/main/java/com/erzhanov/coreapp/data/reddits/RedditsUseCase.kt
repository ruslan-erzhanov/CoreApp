package com.erzhanov.coreapp.data.reddits

import com.erzhanov.coreapp.data.reddits.model.Reddit
import com.erzhanov.coreapp.data.reddits.model.RedditsPage
import com.erzhanov.coreapp.data.reddits.repos.RedditsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditsUseCase @Inject constructor(
    private val repository: RedditsRepository
) {
    fun getSavedReddits(): Flow<List<Reddit>> = repository.getSavedReddits()
    suspend fun fetchReddits(after: String = ""): RedditsPage = repository.fetchReddits(after)
    suspend fun saveReddit(reddit: Reddit) = repository.saveReddit(reddit)
    suspend fun getSavedReddit(id: String): Reddit = repository.getSavedReddit(id)
    suspend fun deleteReddit(id: String) = repository.deleteReddit(id)
    suspend fun deleteAll() = repository.deleteAll()
}