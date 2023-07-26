package com.erzhanov.coreapp.data.auth

import com.erzhanov.coreapp.data.auth.repos.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun signUp() = repository.mockSession()
    suspend fun signIn(login: String, password: String) = repository.signIn(login, password)
}