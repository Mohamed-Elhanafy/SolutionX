package com.example.solutionx.features.authentication.domain.repository.remote

import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto

internal interface RemoteDataSource {
    fun loginWithEmailPassword(email: String, password: String): LoginResponseDto?

    fun loginWithSocial(token: String): LoginResponseDto?

    suspend fun loginWithPhone(phoneNumber: String): LoginResponseDto?
}