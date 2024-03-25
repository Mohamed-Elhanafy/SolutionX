package com.example.solutionx.features.authentication.data.repositoty.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_NAME
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_PREFERENCES
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow


val Context.dataStore by preferencesDataStore(USER_PREFERENCES)

class UserPreferences(private val context: Context) {


    val isUserLoggedIn = context.dataStore.data.map { preferences ->
        preferences[IS_USER_LOGGED_IN] ?: false
    }

    val userName = context.dataStore.data.map { preferences ->
        preferences[USER_NAME] ?: ""
    }

    suspend fun saveUserLoggedInState(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }

    suspend fun saveUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }
}