package com.example.solutionx.feature.login.domain.models

import com.example.solutionx.feature.login.data.model.entity.Gender

data class User(
    val id: String,
    val name: String,
    val gender: Gender
)