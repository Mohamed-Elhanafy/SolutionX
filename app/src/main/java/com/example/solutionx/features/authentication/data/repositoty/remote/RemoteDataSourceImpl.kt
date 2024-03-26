package com.example.solutionx.features.authentication.data.repositoty.remote

import com.example.solutionx.common.data.remote.RetrofitApi
import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val retrofitApi: RetrofitApi
) : RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): LoginResponseDto? {
        return null
    }

    override fun loginWithSocial(token: String): LoginResponseDto? {
        return null

    }

    override suspend fun loginWithPhone(countryCode:String ,phoneNumber: String , password: String ): LoginResponseDto? {
        return retrofitApi.login(countryCode , phoneNumber , password)
    }


}