package com.example.solutionx.features.authentication.data.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.features.authentication.domain.repository.local.KeyValueStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class DataStoreStorage(private val context: Context) : KeyValueStorage {
    private val Context.dataStore by preferencesDataStore("user_preferences")

    override suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun getString(key: String): String {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: ""
        }.first()
    }

    override suspend fun saveInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    override suspend fun getInt(key: String): Int {
        return context.dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)] ?: -1
        }.first()
    }

    override suspend fun saveBoolean(key: String, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: false
        }.first()
    }
}