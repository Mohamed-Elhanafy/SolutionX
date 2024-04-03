package com.example.solutionx.common.data.repository.local

import android.content.Context
import android.util.Base64
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreStorage(private val context: Context) : KeyValueStorage {
    private val Context.dataStore by preferencesDataStore("user_preferences")

    override suspend fun <K, V> save(key: K, value: V) {
        context.dataStore.edit { preferences ->
            val stringKey = key.toString()
            when (value) {
                is String -> preferences[stringPreferencesKey(stringKey)] = value
                is Int -> preferences[intPreferencesKey(stringKey)] = value
                is Boolean -> preferences[booleanPreferencesKey(stringKey)] = value

                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <K, V> get(key: K): V {
        val stringKey = key.toString()
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(stringKey)] as? V
                ?: preferences[intPreferencesKey(stringKey)] as? V
                ?: preferences[booleanPreferencesKey(stringKey)] as? V
                ?: throw IllegalArgumentException("Unsupported type")
        }.first()
    }
}