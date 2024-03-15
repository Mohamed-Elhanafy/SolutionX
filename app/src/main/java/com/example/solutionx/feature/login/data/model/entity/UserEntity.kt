package com.example.solutionx.feature.login.data.model.entity

import com.example.solutionx.feature.login.domain.models.enums.Gender

data class UserEntity(
    val userId: String,
    val username: String,
    val gender: Gender
)
