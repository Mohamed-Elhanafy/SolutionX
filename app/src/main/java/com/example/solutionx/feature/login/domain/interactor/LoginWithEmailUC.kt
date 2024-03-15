package com.example.solutionx.feature.login.domain.interactor

import com.example.solutionx.feature.login.data.mappers.UserMapper
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.models.User
import com.example.solutionx.feature.login.domain.repository.UserRepository

class LoginWithEmailUC(
    private val userRepository: UserRepository,
    private val localDataSource: LocalDataSource,
) {
    suspend operator fun invoke(email: String, password: String): User {
        val user = userRepository.loginWithEmailPassword(email, password)
        val userEntity = UserMapper.mapToEntity(user)
        localDataSource.saveUser(userEntity)
        return user
    }
}