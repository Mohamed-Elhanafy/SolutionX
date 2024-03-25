package com.example.solutionx.common.data.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val BASE_URL = "https://dev.api.altashirat.solutionplus.net/api/"


    object DataStoreKeys {
        const val USER_PREFERENCES = "user_preferences"
        val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
        val USER_NAME = stringPreferencesKey("user_name")
    }
}

