package com.example.savoria.data


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "Savoria"
class DataStoreManager(context: Context) {
    private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)
    private val dataStore = context.dataStore

    companion object {
        val TOKEN = stringPreferencesKey("token")
        val USER_ID = intPreferencesKey("user_id")
    }

    suspend fun saveToken(token: String, user_id: Int) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[USER_ID] = user_id
        }
    }

    val getToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[TOKEN]
    }

    val getUserid: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

}