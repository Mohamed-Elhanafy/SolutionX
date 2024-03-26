package com.example.solutionx.presentation.screens

sealed class LoginIntent {

    data class LoginWithEmail(val email: String, val password: String) : LoginIntent()
    data class LoginWithSocial(val token: String) : LoginIntent()
    data class LoginWithPhone(val countryCode:String ,val phone: String , val password: String) : LoginIntent()
}
