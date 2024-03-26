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


import com.google.gson.Gson
import androidx.datastore.preferences.core.stringPreferencesKey

class DataStoreStorage(private val context: Context) : KeyValueStorage {
    private val Context.dataStore by preferencesDataStore("user_preferences")

    override suspend fun <T> save(key: String, value: T) {
        context.dataStore.edit { preferences ->
            when (value) {
                is String -> preferences[stringPreferencesKey(key)] = value
                is Int -> preferences[intPreferencesKey(key)] = value
                is Boolean -> preferences[booleanPreferencesKey(key)] = value
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> get(key: String): T {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] as? T
                ?: preferences[intPreferencesKey(key)] as? T
                ?: preferences[booleanPreferencesKey(key)] as? T
                ?: throw IllegalArgumentException("Unsupported type")
        }.first()
    }
}