package com.example.solutionx.features.authentication.domain.interactor

import android.util.Log
import com.example.solutionx.common.data.extentions.executeNetworkCall
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithPhoneUC(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(phone: String): Flow<Resource<LoginResponse>>{
        return executeNetworkCall {
            val login = loginRepository.loginWithPhone(phone)
            loginRepository.saveLogin(login)
            Log.i("LoginWithPhoneUC", loginRepository.geUser().name)
            login
        }
    }


}