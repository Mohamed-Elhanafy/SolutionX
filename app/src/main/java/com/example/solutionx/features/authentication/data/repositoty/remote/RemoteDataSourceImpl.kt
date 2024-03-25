package com.example.solutionx.features.authentication.data.repositoty.remote

import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.data.model.dto.UserDto
import com.example.solutionx.features.authentication.data.network.LoginApi
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import javax.inject.Inject

internal class RemoteDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi
) : RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): UserDto? {
        return null
    }

    override fun loginWithSocial(token: String): UserDto? {
        return null

    }

    override suspend fun loginWithPhone(phoneNumber: String): UserDto? {
        return loginApi.login("0020" , phoneNumber , "123456789").userDto
    }


}