package com.example.solutionx.features.authentication.domain.repository


import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User

interface LoginRepository {
    suspend fun loginWithPhone(countryCode:String, phone: String , password: String): LoginResponse
    suspend fun loginWithEmailPassword(email: String, password: String): LoginResponse
    suspend fun loginWithSocial(token: String): LoginResponse

    suspend fun saveLogin(login: LoginResponse)

    suspend fun geUser(): User
}