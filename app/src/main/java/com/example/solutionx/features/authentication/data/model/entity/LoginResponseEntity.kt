package com.example.solutionx.features.authentication.data.model.entity

data class LoginResponseEntity (
    val accessToken: String,
    val user: UserEntity
)