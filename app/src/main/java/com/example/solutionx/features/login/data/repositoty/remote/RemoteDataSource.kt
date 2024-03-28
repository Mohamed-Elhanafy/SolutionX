package com.example.solutionx.features.login.data.repositoty.remote

import com.example.solutionx.common.data.repository.remote.RetrofitApi
import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.repository.remote.IRemoteDataSource

internal class RemoteDataSource(
    private val retrofitApi: RetrofitApi
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
        return retrofitApi.login(loginRequest)
    }


}