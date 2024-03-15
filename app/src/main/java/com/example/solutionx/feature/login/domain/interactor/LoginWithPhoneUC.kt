package com.example.solutionx.feature.login.domain.interactor

import com.example.solutionx.feature.login.data.mappers.UserMapper
import com.example.solutionx.feature.login.data.repositoty.UserRepositoryImpl
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.models.User
import com.example.solutionx.feature.login.domain.repository.UserRepository

class LoginWithPhoneUC(
    private val userRepository: UserRepository,
    private val localDataSource: LocalDataSource
) {
    suspend operator fun invoke(phone: String): User {
        val user = userRepository.loginWithPhone(phone)
        val userEntity = UserMapper.mapToEntity(user)
        localDataSource.saveUser(userEntity)
        return user
    }
}