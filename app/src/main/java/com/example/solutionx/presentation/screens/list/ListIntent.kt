package com.example.solutionx.presentation.screens.list

sealed class ListIntent {

    data class SaveListValues(val names: List<String>) : ListIntent()

    data class TranslateListValues(val names: List<String>) : ListIntent()
}