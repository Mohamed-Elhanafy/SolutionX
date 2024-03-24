package com.example.solutionx.features.authentication.domain.interactor

import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithSocialUC (
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(token: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading(true))
            val user = userRepository.loginWithSocial(token)
            val userEntity = UserMapper.mapToEntity(user)
            userRepository.saveUser(userEntity)
            emit(Resource.Success(user))
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