package com.example.solutionx.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.feature.login.domain.interactor.LoginWithEmailUC
import com.example.solutionx.feature.login.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.feature.login.domain.interactor.LoginWithSocialUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithSocialUC: LoginWithSocialUC
) : ViewModel() {


    private val _state = MutableStateFlow<LoginViewState>(LoginViewState.Loading)
    val state: StateFlow<LoginViewState> get() = _state


    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginWithEmail -> {
                viewModelScope.launch {
                    try {
                        loginWithEmailUC(intent.email, intent.password).collect { user ->
                            _state.value = LoginViewState.Success(user)
                        }
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }

            is LoginIntent.LoginWithSocial -> {
                viewModelScope.launch {
                    try {
                        loginWithSocialUC(intent.token).collect { user ->
                            _state.value = LoginViewState.Success(user)
                        }
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }

            is LoginIntent.LoginWithPhone -> {
                viewModelScope.launch {
                    try {
                        loginWithPhoneUC(intent.phone).collect { user ->
                            _state.value = LoginViewState.Success(user)
                        }
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }
        }
    }
}