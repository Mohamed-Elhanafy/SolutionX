package com.example.solutionx.domain.interactor

import com.example.solutionx.data.repositoty.UserRepository
import com.example.solutionx.domain.models.User

class LoginWithPhoneUC(private val userRepository: UserRepository) {
    suspend operator fun invoke(params: String): User {
        return userRepository.loginWithPhone(params)
    }
}