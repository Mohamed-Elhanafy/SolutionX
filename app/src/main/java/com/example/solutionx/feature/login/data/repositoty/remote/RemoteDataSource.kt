package com.example.solutionx.feature.login.data.repositoty.remote

import com.example.solutionx.feature.login.data.model.dto.UserDto

interface RemoteDataSource {
    fun loginWithEmailPassword(email: String, password: String) : UserDto

    fun loginWithSocial(token: String) : UserDto

    fun loginWithPhone(phoneNumber: String) : UserDto
}