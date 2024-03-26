package com.example.solutionx.presentation.screens
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.R
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the state
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is LoginViewState.Loading -> {
                        // Show loading
                    }
                    is LoginViewState.Success -> {
                        // Handle success
                        val login:LoginResponse  = state.login
                        Log.i("Main", login.user.name)
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

        val loginButton = view.findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val intent = LoginIntent.LoginWithPhone("0020","100100100","123456789")
            viewModel.processIntent(intent)
        }
    }
}