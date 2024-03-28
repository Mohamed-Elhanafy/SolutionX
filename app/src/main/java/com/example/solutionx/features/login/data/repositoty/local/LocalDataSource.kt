package com.example.solutionx.features.login.data.repositoty.local

import android.os.Build.USER
import android.util.Log
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.ACCESS_TOKEN
import com.example.solutionx.common.data.constants.Constants.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.solutionx.features.login.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.login.domain.repository.local.ILocalDataSource
import com.google.gson.Gson

internal class LocalDataSource(
    private val keyValueStorage: KeyValueStorage,
    private val gson: Gson
) : ILocalDataSource {



    override suspend fun saveLogin(loginResponse: LoginResponseEntity) {
        Log.d("LocalDataSourceImpl", "saveUser: $loginResponse")
        val userJson = gson.toJson(loginResponse.user)
        keyValueStorage.save(USER, userJson)
        keyValueStorage.save(ACCESS_TOKEN, loginResponse.accessToken)
        keyValueStorage.save(IS_USER_LOGGED_IN, true)
    }
    override suspend fun getAccessToken(): String {
        return keyValueStorage.get(ACCESS_TOKEN)
    }

    override suspend fun getUser(): UserEntity {
        val userJson = keyValueStorage.get<String>(USER)
        return gson.fromJson(userJson, UserEntity::class.java)
    }


}