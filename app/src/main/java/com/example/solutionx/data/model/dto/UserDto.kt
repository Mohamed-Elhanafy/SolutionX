package com.example.solutionx.data.model.dto

import com.example.solutionx.data.model.entity.Gender

data class UserDto(
    val id: String,
    val name: String,
    val gender: Gender
)