package com.example.solutionx.common.data.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val BASE_URL = "https://dev.api.altashirat.solutionplus.net/api/"


    object DataStoreKeys {
        const val USER_PREFERENCES = "user_preferences"
        const val IS_USER_LOGGED_IN = "is_user_logged_in"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_ID = "user_id"
        const val ACCESS_TOKEN = "access_token"
    }
}

