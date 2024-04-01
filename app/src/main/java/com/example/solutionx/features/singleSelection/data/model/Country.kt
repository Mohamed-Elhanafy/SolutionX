package com.example.solutionx.features.singleSelection.data.model

data class Country(
    val code: String,
    val currency: String,
    val flag: String,
    val id: Int,
    val name: String,
    val phone_code: String,
    var isSelected: Boolean
)