import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.feature.login.domain.interactor.LoginWithEmailUC
import com.example.solutionx.feature.login.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.feature.login.domain.interactor.LoginWithSocialUC
import com.example.solutionx.presentation.screens.LoginIntent
import com.example.solutionx.presentation.screens.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
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
                        val user = loginWithEmailUC(intent.email, intent.password)
                        _state.value = LoginViewState.Success(user)
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }
            is LoginIntent.LoginWithSocial -> {
                viewModelScope.launch {
                    try {
                        val user = loginWithSocialUC(intent.token)
                        _state.value = LoginViewState.Success(user)
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }
            is LoginIntent.LoginWithPhone -> {
                viewModelScope.launch {
                    try {
                        val user = loginWithPhoneUC(intent.phone)
                        _state.value = LoginViewState.Success(user)
                    } catch (e: Exception) {
                        _state.value = LoginViewState.Error(e)
                    }
                }
            }
        }
    }
}