package com.erzhanov.coreapp.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.erzhanov.coreapp.data.common.prefs.PrefsDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import java.util.UUID

class PrefsDataSourceImpl(
    private val context: Context,
    private val json: Json
) : PrefsDataSource {

    private val Context.dataStore by preferencesDataStore("preferences")

    override suspend fun clearData() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }

    override suspend fun getDeviceUUID(): String {
        val uuid = context.dataStore.data.map { it[Keys.DEVICE_UUID] }.first()
        if (uuid != null) return uuid

        val generatedUUID = UUID.randomUUID().toString()
        context.dataStore.edit { preferences ->
            preferences[Keys.DEVICE_UUID] = generatedUUID
        }
        return generatedUUID
    }

    override suspend fun getUserToken(): String? {
        return context.dataStore.data.map { it[Keys.TOKEN] }.first()
    }

    override suspend fun saveUserToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.TOKEN] = token
        }
    }

    override suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(Keys.TOKEN)
        }
    }

    override suspend fun isFirstLaunch(): Boolean {
        val isFirstLaunch = context.dataStore.data.map { prefs ->
            prefs[Keys.FIRST_LAUNCH] ?: true
        }.first()

        context.dataStore.edit { prefs ->
            prefs[Keys.FIRST_LAUNCH] = false
        }

        return isFirstLaunch
    }


    private object Keys {
        val DEVICE_UUID = stringPreferencesKey("device_uuid")
        val TOKEN = stringPreferencesKey("token")


        val FIRST_LAUNCH = booleanPreferencesKey("first_launch")

    }
}
