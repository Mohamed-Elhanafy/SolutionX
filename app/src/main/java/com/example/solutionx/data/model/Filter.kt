package com.example.solutionx.data.model

import com.example.solutionx.ui.adapter.ListItem

data class Filter(
    override val id: Int,
    override val name: String,
    override var isSelected: Boolean
): ListItem