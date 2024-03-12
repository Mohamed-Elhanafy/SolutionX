package com.example.solutionx.feature.login.domain.interactor

import com.example.solutionx.feature.login.data.repositoty.UserRepository
import com.example.solutionx.feature.login.domain.models.User

class LoginWithEmailUC(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): User {
        return userRepository.loginWithEmailPassword(email, password)
    }
}