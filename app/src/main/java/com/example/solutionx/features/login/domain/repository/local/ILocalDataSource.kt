package com.example.solutionx.features.login.domain.repository.local

import com.example.solutionx.features.login.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity

internal interface ILocalDataSource {
    suspend fun saveLogin(loginResponse: LoginResponseEntity)
    suspend fun getAccessToken(): String
    suspend fun getUser(): UserEntity
}