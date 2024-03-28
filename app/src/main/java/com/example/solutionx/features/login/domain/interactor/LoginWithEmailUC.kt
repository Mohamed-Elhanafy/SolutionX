package com.example.solutionx.features.login.domain.interactor

import com.example.solutionx.common.data.extentions.wrapper
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithEmailUC(
    private val loginRepository: ILoginRepository,
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<LoginResponse>> {
        return wrapper<LoginResponse> {
            val login = loginRepository.loginWithEmailPassword(email, password)
            loginRepository.saveLogin(login)
            login
        }
    }
}