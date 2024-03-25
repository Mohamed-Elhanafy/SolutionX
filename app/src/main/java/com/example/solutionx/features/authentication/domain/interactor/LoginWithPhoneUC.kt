package com.example.solutionx.features.authentication.domain.interactor

import android.util.Log
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.features.authentication.data.mappers.LoginResponseMapper
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.data.repositoty.local.UserPreferences
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithPhoneUC (
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(phone: String): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading(true))

            val login = loginRepository.loginWithPhone(phone)
            val loginEntity = LoginResponseMapper.domainToEntity(login)
            loginRepository.saveUser(login)

/*
            // Save user's logged-in state and username
            userPreferences.saveUserLoggedInState(true)
            userPreferences.saveUserName(login.user.name)*/

            emit(Resource.Success(login))

        } catch (e: SolutionXException) {
            when (e) {
                is SolutionXException.NoNetworkConnection -> {
                    emit(Resource.Failure(e))
                }

                is SolutionXException.HttpException -> {
                    emit(Resource.Failure(e))

                }

                is SolutionXException.IOException -> {
                    emit(Resource.Failure(e))
                }
            }
        }
    }
}