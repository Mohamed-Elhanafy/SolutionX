package com.example.solutionx.presentation.screens.login

import com.example.solutionx.features.login.domain.models.LoginResponse

sealed class LoginViewState {
    object Loading : LoginViewState()

    object Idle : LoginViewState()
    data class Success(val login: LoginResponse) : LoginViewState()
    data class Error(val error: Throwable) : LoginViewState()


}