package com.example.solutionx.features.login.domain.interactor


import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.models.User
import com.example.solutionx.features.login.domain.repository.ILoginRepository

class FakeLoginRepository : ILoginRepository {
    var loginResponse: LoginResponse? = null

    override suspend fun loginWithPhone(loginRequest: LoginRequest): LoginResponse {
        return loginResponse ?: throw Exception("Test not properly setup")
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): LoginResponse {
        return loginResponse ?: throw Exception("Test not properly setup")
    }

    override suspend fun loginWithSocial(token: String): LoginResponse {
        return loginResponse ?: throw Exception("Test not properly setup")
    }

    override suspend fun saveLogin(login: LoginResponse) {
        // Do nothing for now
    }

    override suspend fun geUser(): User {
        return User(1, "testEmail", "testPhone") // Return a default user
    }
}