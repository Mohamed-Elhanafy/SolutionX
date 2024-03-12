package com.example.solutionx.feature.login.data.repositoty.remote

import com.example.solutionx.feature.login.data.model.dto.UserDto
import com.example.solutionx.feature.login.domain.repository.remote.RemoteDataSource

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