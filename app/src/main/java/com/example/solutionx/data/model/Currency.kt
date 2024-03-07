package com.example.solutionx.data.model

import com.example.solutionx.ui.adapter.ListItem

data class Currency(
    val code: String,
    override val id: Int,
    override val name: String,
    val sign: String,
    override var isSelected: Boolean
): ListItem