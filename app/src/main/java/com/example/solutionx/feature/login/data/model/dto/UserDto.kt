package com.example.solutionx.feature.login.data.model.dto

import com.example.solutionx.feature.login.domain.models.enums.Gender

data class UserDto(
    val id: String,
    val name: String,
    val gender: Gender
)