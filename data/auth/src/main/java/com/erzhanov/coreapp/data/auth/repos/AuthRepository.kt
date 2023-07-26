package com.erzhanov.coreapp.data.auth.repos

import com.erzhanov.coreapp.core.network.ApiContract
import com.erzhanov.coreapp.data.auth.model.SignInRequest
import com.erzhanov.coreapp.data.auth.model.SignUpRequest
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject
import javax.inject.Singleton

interface AuthRepository {
    //todo:remove mocking code
    suspend fun mockSession()
    suspend fun signUp()
    suspend fun signIn(login: String, password: String)
}

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val tokenPreferences: PrefsDataSource,
    private val httpClient: HttpClient,
): AuthRepository {

    //todo:remove mocking code
    //Current api doesn't support Auth functionality, just mocking flows
    override suspend fun mockSession() {
        tokenPreferences.saveUserToken(ApiContract.Headers.AUTHORIZATION)
    }

    override suspend fun signUp() {
        val response: HttpResponse = httpClient.post(ApiContract.Auth.SIGN_UP) {
            setBody(SignUpRequest())
        }
        tokenPreferences.saveUserToken(requireNotNull(response.headers[ApiContract.Headers.AUTHORIZATION]))
    }

    override suspend fun signIn(login: String, password: String) {
        val response: HttpResponse = httpClient.post(ApiContract.Auth.SIGN_UP) {
            setBody(SignInRequest(login, password))
        }
        tokenPreferences.saveUserToken(requireNotNull(response.headers[ApiContract.Headers.AUTHORIZATION]))
    }
}