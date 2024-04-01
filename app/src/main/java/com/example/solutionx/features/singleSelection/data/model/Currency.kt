package com.example.solutionx.features.singleSelection.data.model


data class Currency(
    val code: String,
    val id: Int,
    val name: String,
    val sign: String,
    var isSelected: Boolean
)