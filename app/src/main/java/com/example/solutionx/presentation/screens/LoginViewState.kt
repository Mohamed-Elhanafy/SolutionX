package com.example.solutionx.presentation.screens

import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User

sealed class LoginViewState {
    object Loading : LoginViewState()

    object Idle : LoginViewState()
    data class Success(val login: LoginResponse) : LoginViewState()
    data class Error(val error: Throwable) : LoginViewState()


}