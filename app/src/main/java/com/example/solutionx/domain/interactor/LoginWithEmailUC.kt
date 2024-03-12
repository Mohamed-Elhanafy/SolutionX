package com.example.solutionx.domain.interactor

import com.example.solutionx.data.repositoty.UserRepository
import com.example.solutionx.domain.models.User

class LoginWithEmailUC(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): User {
        return userRepository.loginWithEmailPassword(email, password)
    }
}