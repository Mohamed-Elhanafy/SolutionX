package com.example.solutionx.data.model

import com.example.solutionx.ui.adapter.ListItem

data class Country(
    val code: String,
    val currency: String,
    val flag: String,
    override val id: Int,
    override val name: String,
    val phone_code: String,
    override var isSelected: Boolean
) : ListItem