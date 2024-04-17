package com.example.solutionx.features.login.domain.repository


import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.models.User

interface ILoginRepository {
    suspend fun loginWithPhone(loginRequest: LoginRequest): LoginResponse
    suspend fun loginWithEmailPassword(email: String, password: String): LoginResponse
    suspend fun loginWithSocial(token: String): LoginResponse

    suspend fun saveLogin(login: LoginResponse)
    suspend fun geUser(): User
}