package com.example.solutionx.features.login.domain.interactor

import com.example.solutionx.android.helpers.logger.LoggerFactory
import com.example.solutionx.common.data.extentions.wrapper
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithPhoneUC(
    private val loginRepository: ILoginRepository,
) {
    suspend operator fun invoke(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>> {
        return wrapper {
            val login = loginRepository.loginWithPhone(loginRequest)
            loginRepository.saveLogin(login)
            logger.info( loginRepository.geUser().name)
            login
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LoginWithPhoneUC::class.java)
    }

}