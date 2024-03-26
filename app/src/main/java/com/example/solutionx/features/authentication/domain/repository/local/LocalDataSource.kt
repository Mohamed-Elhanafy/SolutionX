package com.example.solutionx.features.authentication.domain.repository.local

import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.data.model.entity.UserEntity

internal interface LocalDataSource {
    suspend fun saveLogin(loginResponse: LoginResponseEntity)
    suspend fun getAccessToken(): String
    suspend fun getUser(): UserEntity
}