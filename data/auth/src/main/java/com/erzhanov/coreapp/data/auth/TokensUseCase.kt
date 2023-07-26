package com.erzhanov.coreapp.data.auth

import com.erzhanov.coreapp.data.auth.repos.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokensUseCase @Inject constructor(
    private val repository: TokenRepository
) {
    suspend fun getToken() = repository.getUserToken()
    suspend fun clearToken() = repository.clearAllTokens()
    suspend fun saveToken(token: String) = repository.saveToken(token)
}