package com.example.solutionx.data.repositoty.remote

import com.example.solutionx.data.model.dto.UserDto

interface RemoteDataSource {
    fun loginWithEmailPassword(email: String, password: String) : UserDto

    fun loginWithSocial(token: String) : UserDto

    fun loginWithPhone(phoneNumber: String) : UserDto
}