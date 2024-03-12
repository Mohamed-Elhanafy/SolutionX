package com.example.solutionx.domain.interactor

import com.example.solutionx.data.repositoty.UserRepository
import com.example.solutionx.domain.models.User

class LoginWithSocialUC(private val userRepository: UserRepository) {
    suspend operator fun invoke(token: String): User {
        return userRepository.loginWithSocial(token)
    }
}