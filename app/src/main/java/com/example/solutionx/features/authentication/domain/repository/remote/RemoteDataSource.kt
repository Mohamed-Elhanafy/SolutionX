package com.example.solutionx.features.authentication.domain.repository.remote

import com.example.solutionx.features.authentication.data.model.dto.UserDto

internal interface RemoteDataSource {
    fun loginWithEmailPassword(email: String, password: String): UserDto?

    fun loginWithSocial(token: String): UserDto?

    suspend fun loginWithPhone(phoneNumber: String): UserDto?
}