package com.example.solutionx.features.authentication.domain.interactor

import com.example.solutionx.common.data.extentions.executeNetworkCall
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithEmailUC(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<LoginResponse>> {
        return executeNetworkCall {
            val login = loginRepository.loginWithEmailPassword(email, password)
            loginRepository.saveLogin(login)
            login
        }
    }
}