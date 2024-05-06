package com.example.solutionx.presentation.screens.login

import com.example.solutionx.android.helpers.logger.LoggerFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.common.presentaion.BaseFragment
import com.example.solutionx.databinding.FragmentLoginBinding
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.PhoneRequest
import com.example.solutionx.features.login.domain.models.LoginResponse

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()

    override val bindingClass = FragmentLoginBinding::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the state
        observeLoginState()


        binding.loginButton.setOnClickListener {
            val loginRequest = LoginRequest(
                phone = PhoneRequest(
                    countryCode = "0020",
                    number = "100100100"
                ),
                password = "123456789"
            )

            val intent = LoginIntent.LoginWithPhone(loginRequest)
            viewModel.processIntent(intent)
        }
    }

    private fun observeLoginState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is LoginViewState.Loading -> {
                        // Show loading
                    }

                    is LoginViewState.Success -> {
                        // Handle success
                        val login: LoginResponse = state.login
                        fragmentLogger.info(login.user.name)

                    }

                    is LoginViewState.Error -> {
                        // Handle error
                        val error: Throwable = state.error
                    }

                    is LoginViewState.Idle -> {
                        // Handle idle
                    }
                }
            }
        }
    }

    companion object {
        private val fragmentLogger = LoggerFactory.getLogger(LoginFragment::class.java)
    }


}
