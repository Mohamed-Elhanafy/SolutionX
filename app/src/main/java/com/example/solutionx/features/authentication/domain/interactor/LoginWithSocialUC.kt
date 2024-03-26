package com.example.solutionx.features.authentication.domain.interactor

import com.example.solutionx.common.data.extentions.executeNetworkCall
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithSocialUC(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(token: String): Flow<Resource<LoginResponse>> {
        return executeNetworkCall {
            val login = loginRepository.loginWithSocial(token)
            loginRepository.saveLogin(login)
            login
        }
    }
}