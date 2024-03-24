package com.example.solutionx.features.authentication.domain.repository


import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.models.User

 interface UserRepository {
    suspend fun loginWithPhone(phone: String): User
    suspend fun loginWithEmailPassword(email: String, password: String): User
    suspend fun loginWithSocial(token: String): User

    suspend fun saveUser(user: UserEntity)
}