package com.example.solutionx.features.authentication.data.model.dto

import com.example.solutionx.features.authentication.domain.models.enums.Gender

data class UserDto(
    val id: String,
    val name: String,
    val gender: Gender
)