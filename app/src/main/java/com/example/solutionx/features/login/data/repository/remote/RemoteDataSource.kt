package com.example.solutionx.features.login.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.repository.remote.IRemoteDataSource

internal class RemoteDataSource(
    private val provider: IRestApiNetworkProvider
) : IRemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): LoginResponseDto? {
        return null
    }

    override fun loginWithSocial(token: String): LoginResponseDto? {
        return null

    }

    override suspend fun loginWithPhone(
        loginRequest: LoginRequest
    ): LoginResponseDto {
        return provider.post(
            responseWrappedModel = LoginResponseDto::class.java,
            pathUrl = "login",
            requestBody = loginRequest
        )
    }


}