package com.example.solutionx.features.authentication.data.repositoty.remote

import com.example.solutionx.features.authentication.data.model.dto.UserDto
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource

internal class RemoteDataSourceImpl : RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): UserDto? {
        return null
    }

    override fun loginWithSocial(token: String): UserDto? {
        return null

    }

    override fun loginWithPhone(phoneNumber: String): UserDto? {
        return null
    }
}