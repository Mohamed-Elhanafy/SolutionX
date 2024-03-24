package com.example.solutionx.features.authentication.domain.models

import com.example.solutionx.features.authentication.domain.models.enums.Gender

data class User(
    val id: String,
    val name: String,
    val gender: Gender
)