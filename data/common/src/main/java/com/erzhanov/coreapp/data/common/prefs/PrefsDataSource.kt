package com.erzhanov.coreapp.data.common.prefs

interface PrefsDataSource {
    suspend fun clearData()
    suspend fun clearToken()
    suspend fun getDeviceUUID(): String
    suspend fun getUserToken(): String?
    suspend fun saveUserToken(token: String)
    suspend fun isFirstLaunch(): Boolean
}
