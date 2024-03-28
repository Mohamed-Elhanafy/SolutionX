package com.example.solutionx.features.login.data.repositoty


import com.example.solutionx.features.login.data.mappers.LoginResponseMapper
import com.example.solutionx.features.login.data.mappers.UserMapper
import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.models.User
import com.example.solutionx.features.login.domain.repository.remote.IRemoteDataSource
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import com.example.solutionx.features.login.domain.repository.local.ILocalDataSource

internal class LoginRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : ILoginRepository {
    override suspend fun loginWithPhone(
        loginRequest: LoginRequest
    ): LoginResponse {
        val result: LoginResponseDto? =
            remoteDataSource.loginWithPhone(loginRequest)
        return LoginResponseMapper.dtoToDomain(result!!)
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): LoginResponse {
        val result: LoginResponseDto? = remoteDataSource.loginWithEmailPassword(email, password)
        return LoginResponseMapper.dtoToDomain(result!!)
    }

    override suspend fun loginWithSocial(token: String): LoginResponse {
        val result: LoginResponseDto? = remoteDataSource.loginWithSocial(token)
        return LoginResponseMapper.dtoToDomain(result!!)
    }

    override suspend fun saveLogin(login: LoginResponse) {
        val result = LoginResponseMapper.domainToEntity(login)
        localDataSource.saveLogin(result)
    }

    override suspend fun geUser(): User {
        val result = localDataSource.getUser()
        return UserMapper.entityToDomain(result)
    }
}