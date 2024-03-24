package com.example.solutionx.features.authentication.domain.interactor

import com.example.solutionx.common.Resource
import com.example.solutionx.common.SolutionXException
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithEmailUC(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading(true))
            val user = userRepository.loginWithEmailPassword(email, password)
            val userEntity = UserMapper.mapToEntity(user)
            userRepository.saveUser(userEntity)
            emit(Resource.Success(user))
        } catch (e: SolutionXException) {
            // Handle SolutionXException
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