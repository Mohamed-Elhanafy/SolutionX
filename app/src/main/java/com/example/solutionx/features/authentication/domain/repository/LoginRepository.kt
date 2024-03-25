package com.example.solutionx.features.authentication.domain.repository


import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.models.LoginResponse

interface LoginRepository {
    suspend fun loginWithPhone(phone: String): LoginResponse
    suspend fun loginWithEmailPassword(email: String, password: String): LoginResponse
    suspend fun loginWithSocial(token: String): LoginResponse

    suspend fun saveUser(login: LoginResponse)
}