package com.example.solutionx.features.authentication.data.repositoty.remote

import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi
) : RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): LoginResponseDto? {
        return null
    }

    override fun loginWithSocial(token: String): LoginResponseDto? {
        return null

    }

    override suspend fun loginWithPhone(phoneNumber: String): LoginResponseDto? {
        return loginApi.login("0020" , phoneNumber , "123456789")
    }


}