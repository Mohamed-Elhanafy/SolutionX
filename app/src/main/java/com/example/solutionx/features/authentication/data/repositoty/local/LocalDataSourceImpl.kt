package com.example.solutionx.features.authentication.data.repositoty.local

import android.os.Build.USER
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
import com.google.gson.Gson

internal class LocalDataSourceImpl(
    private val keyValueStorage: KeyValueStorage,
    private val gson: Gson
) : LocalDataSource {


/*    override suspend fun saveLogin(loginResponse: LoginResponseEntity) {
        Log.d("LocalDataSourceImpl", "saveUser: $loginResponse")
        keyValueStorage.save(USER_NAME, loginResponse.user.name)
        keyValueStorage.save(USER_EMAIL, loginResponse.user.email)
        keyValueStorage.save(USER_ID, loginResponse.user.userId)
        keyValueStorage.save(ACCESS_TOKEN, loginResponse.accessToken)
        keyValueStorage.save(IS_USER_LOGGED_IN, true)
    }*/
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

/*    override suspend fun getUser(): UserEntity {
        val userName = keyValueStorage.get<String>(USER_NAME)
        val userEmail = keyValueStorage.get<String>(USER_EMAIL)
        val userId = keyValueStorage.get<Int>(USER_ID)

        return UserEntity(userId, userName, userEmail)
    }*/

    override suspend fun getUser(): UserEntity {
        val userJson = keyValueStorage.get<String>(USER)
        return gson.fromJson(userJson, UserEntity::class.java)
    }


}