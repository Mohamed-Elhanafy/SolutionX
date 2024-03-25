package com.example.solutionx.features.authentication.domain.interactor

import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.features.authentication.data.mappers.LoginResponseMapper
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithSocialUC(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(token: String): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading(true))

            val login = loginRepository.loginWithSocial(token)
            val loginEntity = LoginResponseMapper.domainToEntity(login)
            loginRepository.saveUser(login)

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