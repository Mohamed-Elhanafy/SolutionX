package com.example.solutionx.domain.models

import com.example.solutionx.data.model.entity.Gender

data class User(
    val id: String,
    val name: String,
    val gender: Gender
)