package com.example.solutionx.feature.login.domain.interactor

import com.example.solutionx.feature.login.data.mappers.UserMapper
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.models.User
import com.example.solutionx.feature.login.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginWithPhoneUC @Inject constructor(
    private val userRepository: UserRepository,
    private val localDataSource: LocalDataSource
) {
    suspend operator fun invoke(phone: String): Flow<User> = flow{
        val user = userRepository.loginWithPhone(phone)
        val userEntity = UserMapper.mapToEntity(user)
        localDataSource.saveUser(userEntity)
        emit(user)
    }
}