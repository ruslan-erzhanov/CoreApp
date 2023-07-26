package com.erzhanov.coreapp.data.reddits.repos

import com.erzhanov.coreapp.core.network.ApiContract
import com.erzhanov.coreapp.data.reddits.model.ApiReddit
import com.erzhanov.coreapp.data.reddits.model.Reddit
import com.erzhanov.coreapp.data.reddits.model.RedditsPage
import com.erzhanov.coreapp.data.reddits.model.RootList
import com.erzhanov.coreapp.data.reddits.model.asDomain
import com.erzhanov.coreapp.data.reddits.model.asEntity
import com.erzhanov.coreapp.data.reddits.model.toDomainModel
import com.erzhanov.coreapp.data.storage.db.RedditsDao
import com.erzhanov.coreapp.data.storage.model.EntityReddit
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface RedditsRepository {
    fun getSavedReddits(): Flow<List<Reddit>>
    suspend fun fetchReddits(after: String): RedditsPage
    suspend fun saveReddit(reddit: Reddit)
    suspend fun getSavedReddit(id: String): Reddit
    suspend fun deleteReddit(id: String)
    suspend fun deleteAll()
}

@Singleton
internal class RedditsRepositoryImpl @Inject constructor(
    private val redditsDao: RedditsDao,
    private val httpClient: HttpClient,
): RedditsRepository {

    override fun getSavedReddits(): Flow<List<Reddit>> {
        return redditsDao.getAllReddits().map { it.map(EntityReddit::asDomain) }
    }

    override suspend fun fetchReddits(after: String): RedditsPage {
        val rootList: RootList = httpClient.get(ApiContract.Reddit.TOP) {
            url {
                if (after.isNotEmpty()) parameters.append(ApiContract.Params.AFTER, after)
                parameters.append(ApiContract.Params.LIMIT, "10")
            }
        }.body()
        val netList: List<ApiReddit> = rootList.data.children.map { it.data }
        val ids = netList.map { it.id }
        val savedList = redditsDao.getReddits(ids)
        val result = when {
            savedList.isEmpty() -> netList.map { it.toDomainModel() }
            else -> {
                netList.map { apiReddit ->
                    val saved = savedList
                        .find { reddit -> reddit.id == apiReddit.id } != null
                    apiReddit.toDomainModel(saved = saved)
                }
            }
        }
        return RedditsPage(
            items = result,
            rootList.data.after.orEmpty()
        )
    }

    override suspend fun saveReddit(reddit: Reddit) {
        redditsDao.insertReddit(reddit.asEntity)
    }

    override suspend fun getSavedReddit(id: String): Reddit {
        return redditsDao.getReddit(id).asDomain
    }

    override suspend fun deleteReddit(id: String) {
        redditsDao.deleteReddit(id)
    }

    override suspend fun deleteAll() {
        redditsDao.deleteAll()
    }
}