package com.example.solutionx.features.authentication.data.repositoty.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.ACCESS_TOKEN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_EMAIL
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_ID
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_NAME
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_PREFERENCES
import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow


val Context.dataStore by preferencesDataStore(USER_PREFERENCES)

class UserPreferences(private val context: Context) {


    val isUserLoggedIn = context.dataStore.data.map { preferences ->
        preferences[IS_USER_LOGGED_IN] ?: false
    }



    suspend fun getAccessToken(): String {
        return context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }.first()
    }

    suspend fun saveLogin(loginResponse: LoginResponseEntity) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = loginResponse.user.name
            preferences[USER_EMAIL] = loginResponse.user.email
            preferences[USER_ID] = loginResponse.user.userId
            preferences[ACCESS_TOKEN] = loginResponse.accessToken
            preferences[IS_USER_LOGGED_IN] = true
        }
    }
    suspend fun getUser(): UserEntity {

        val userName = context.dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: ""
        }.first()

        val userEmail = context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }.first()

        val userId = context.dataStore.data.map { preferences ->
            preferences[USER_ID] ?: -1
        }.first()

        return UserEntity(userId, userName, userEmail)
    }


}