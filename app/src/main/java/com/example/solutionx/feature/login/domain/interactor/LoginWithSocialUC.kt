package com.example.solutionx.feature.login.domain.interactor

import com.example.solutionx.feature.login.data.repositoty.UserRepository
import com.example.solutionx.feature.login.domain.models.User

class LoginWithSocialUC(private val userRepository: UserRepository) {
    suspend operator fun invoke(token: String): User {
        return userRepository.loginWithSocial(token)
    }
}