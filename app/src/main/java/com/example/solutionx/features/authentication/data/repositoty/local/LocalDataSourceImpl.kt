package com.example.solutionx.features.authentication.data.repositoty.local

import android.util.Log
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.ACCESS_TOKEN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_EMAIL
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_ID
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.USER_NAME
import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource

internal class LocalDataSourceImpl(
    private val keyValueStorage: KeyValueStorage

) : LocalDataSource {


    override suspend fun saveLogin(loginResponse: LoginResponseEntity) {
        Log.d("LocalDataSourceImpl", "saveUser: $loginResponse")
        keyValueStorage.saveString(USER_NAME, loginResponse.user.name)
        keyValueStorage.saveString(USER_EMAIL, loginResponse.user.email)
        keyValueStorage.saveInt(USER_ID, loginResponse.user.userId)
        keyValueStorage.saveString(ACCESS_TOKEN, loginResponse.accessToken)
        keyValueStorage.saveBoolean(IS_USER_LOGGED_IN, true)
    }

    override suspend fun getAccessToken(): String {
        return keyValueStorage.getString(ACCESS_TOKEN)
    }

    override suspend fun getUser(): UserEntity {
        val userName = keyValueStorage.getString(USER_NAME)
        val userEmail = keyValueStorage.getString(USER_EMAIL)
        val userId = keyValueStorage.getInt(USER_ID)

        return UserEntity(userId, userName, userEmail)
    }


}