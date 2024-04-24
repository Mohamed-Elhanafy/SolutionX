package com.example.solutionx.presentation.screens.list

import com.example.solutionx.features.login.domain.models.LoginResponse

sealed class ListViewState {
    object Loading : ListViewState()

    object Idle : ListViewState()
    data class Success(val data: List<String>) : ListViewState()
    data class Error(val error: Throwable) : ListViewState()


}