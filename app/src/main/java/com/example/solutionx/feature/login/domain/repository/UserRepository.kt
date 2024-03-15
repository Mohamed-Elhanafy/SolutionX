package com.example.solutionx.feature.login.domain.repository


import com.example.solutionx.feature.login.domain.models.User

interface UserRepository {
    suspend fun loginWithPhone(phone: String): User
    suspend fun loginWithEmailPassword(email: String, password: String): User
    suspend fun loginWithSocial(token: String): User
}