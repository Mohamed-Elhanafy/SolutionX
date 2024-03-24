package com.example.solutionx.features.authentication.data.repositoty.remote

import com.example.solutionx.features.authentication.data.model.dto.UserDto
import com.example.solutionx.features.authentication.domain.models.enums.Gender
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource

internal class RemoteDataSourceImpl : RemoteDataSource {
    override fun loginWithEmailPassword(email: String, password: String): UserDto {
        return UserDto("1  " , "Ahmed" , Gender.MALE)
    }

    override fun loginWithSocial(token: String): UserDto {
        return UserDto("1  " , "Ahmed" , Gender.MALE)

    }

    override fun loginWithPhone(phoneNumber: String): UserDto {
        return UserDto("1  " , "Ahmed" , Gender.MALE)
    }
}