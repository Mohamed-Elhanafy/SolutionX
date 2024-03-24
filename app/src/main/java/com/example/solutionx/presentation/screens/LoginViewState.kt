package com.example.solutionx.presentation.screens

import com.example.solutionx.features.authentication.domain.models.User

sealed class LoginViewState {
    object Loading : LoginViewState()

    object idle : LoginViewState()
    data class Success(val user: User) : LoginViewState()
    data class Error(val error: Throwable) : LoginViewState()


}