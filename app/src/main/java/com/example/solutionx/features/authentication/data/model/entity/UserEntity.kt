package com.example.solutionx.features.authentication.data.model.entity

import com.example.solutionx.features.authentication.domain.models.enums.Gender

data class UserEntity(
    val userId: String,
    val username: String,
    val gender: Gender
)
