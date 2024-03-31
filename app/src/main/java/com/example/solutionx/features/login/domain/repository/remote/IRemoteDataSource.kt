package com.example.solutionx.features.login.domain.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest

internal interface IRemoteDataSource {
    fun loginWithEmailPassword(email: String, password: String): LoginResponseDto?

    fun loginWithSocial(token: String): LoginResponseDto?

    suspend fun loginWithPhone(
        loginRequest: LoginRequest
    ): LoginResponseDto?
}