package com.example.solutionx.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.Resource
import com.example.solutionx.features.authentication.domain.interactor.LoginWithEmailUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithSocialUC
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


    private val _state = MutableStateFlow<LoginViewState>(LoginViewState.idle)
    val state: StateFlow<LoginViewState> get() = _state


    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginWithEmail -> loginWithEmail(intent)
            is LoginIntent.LoginWithSocial -> loginWithSocial(intent)
            is LoginIntent.LoginWithPhone -> loginWithPhone(intent)
        }
    }

    private fun loginWithEmail(intent: LoginIntent.LoginWithEmail) {
        viewModelScope.launch {
            loginWithEmailUC(intent.email, intent.password).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        //loading
                    }

                    is Resource.Success -> {
                        _state.value = LoginViewState.Success(resource.data)
                    }

                    is Resource.Failure -> {
                        _state.value = LoginViewState.Error(resource.exception)
                    }
                }
            }
        }
    }

    private fun loginWithSocial(intent: LoginIntent.LoginWithSocial) {
        viewModelScope.launch {
            loginWithSocialUC(intent.token).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // loading
                    }

                    is Resource.Success -> {
                        _state.value = LoginViewState.Success(resource.data)
                    }

                    is Resource.Failure -> {
                        _state.value = LoginViewState.Error(resource.exception)
                    }
                }
            }
        }
    }

    private fun loginWithPhone(intent: LoginIntent.LoginWithPhone) {
        viewModelScope.launch {
            loginWithPhoneUC(intent.phone).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Loading
                    }

                    is Resource.Success -> {
                        _state.value = LoginViewState.Success(resource.data)
                    }

                    is Resource.Failure -> {
                        _state.value = LoginViewState.Error(resource.exception)
                    }
                }
            }
        }
    }
}