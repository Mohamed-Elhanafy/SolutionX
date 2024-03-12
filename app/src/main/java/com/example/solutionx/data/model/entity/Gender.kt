package com.example.solutionx.data.model.entity

enum class Gender(val value: Int) {
    UNDEFINED(0),
    MALE(1),
    FEMALE(2)
}

fun getGenderFromValue(value: Int): Gender {
    return when (value) {
        0 -> Gender.UNDEFINED
        1 -> Gender.MALE
        2 -> Gender.FEMALE
        else -> throw IllegalArgumentException("Invalid gender value: $value")
    }
}