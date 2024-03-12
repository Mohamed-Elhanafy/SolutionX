package com.example.solutionx.data.repositoty.remote

import com.example.solutionx.data.model.dto.UserDto

class RemoteDataSourceImpl: RemoteDataSource{
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