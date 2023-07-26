package com.erzhanov.coreapp.data.auth.repos

import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import javax.inject.Inject
import javax.inject.Singleton

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun clearAllTokens()
    suspend fun getUserToken(): String?
}

@Singleton
internal class TokenRepositoryImpl  @Inject constructor(
    private val tokenPreferences: PrefsDataSource,
): TokenRepository {

    override suspend fun saveToken(token: String) {
        tokenPreferences.saveUserToken(token)
    }

    override suspend fun clearAllTokens() {
        tokenPreferences.clearToken()
    }

    override suspend fun getUserToken(): String? {
        return tokenPreferences.getUserToken()
    }
}