package com.example.solutionx.feature.login.data.remote

import com.example.solutionx.feature.login.data.model.dto.UserDto
import com.example.solutionx.feature.login.domain.remote.RemoteDataSource

class RemoteDataSourceImpl: RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun loginWithSocial(token: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun loginWithPhone(phoneNumber: String): UserDto {
        TODO("Not yet implemented")
    }
}