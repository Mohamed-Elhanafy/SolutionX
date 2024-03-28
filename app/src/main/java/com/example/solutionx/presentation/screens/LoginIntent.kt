package com.example.solutionx.presentation.screens

import com.example.solutionx.features.login.data.model.request.LoginRequest

sealed class LoginIntent {
    data class LoginWithEmail(val email: String, val password: String) : LoginIntent()
    data class LoginWithSocial(val token: String) : LoginIntent()
    data class LoginWithPhone(val loginRequest: LoginRequest) : LoginIntent()
}
