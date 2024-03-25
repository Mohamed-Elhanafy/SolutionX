package com.example.solutionx.features.authentication.data.repositoty.local

import android.util.Log
import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource

internal class LocalDataSourceImpl(
    private val userPreferences: UserPreferences

) : LocalDataSource {


    override suspend fun saveUser(loginResponse: LoginResponseEntity) {
        Log.d("LocalDataSourceImpl", "saveUser: $loginResponse")
        userPreferences.saveUserLoggedInState(true)
        userPreferences.saveUserName(loginResponse.user.name)
    }

    override suspend fun getUser(userId: String): UserEntity {
        return UserEntity(-1 ,"", "" )
    }


}