package com.example.solutionx.features.authentication.data.repositoty


import com.example.solutionx.features.authentication.data.mappers.LoginResponseMapper
import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto
import com.example.solutionx.features.authentication.domain.models.LoginResponse
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : LoginRepository {
    override suspend fun loginWithPhone(countryCode:String, phone: String , password: String): LoginResponse {
        val result: LoginResponseDto? = remoteDataSource.loginWithPhone(countryCode , phone , password)
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