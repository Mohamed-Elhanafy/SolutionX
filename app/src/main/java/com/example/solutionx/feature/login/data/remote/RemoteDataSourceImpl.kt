package com.example.solutionx.feature.login.data.remote

import com.example.solutionx.feature.login.data.model.dto.UserDto
import com.example.solutionx.feature.login.domain.models.enums.Gender
import com.example.solutionx.feature.login.domain.remote.RemoteDataSource

class RemoteDataSourceImpl : RemoteDataSource {
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