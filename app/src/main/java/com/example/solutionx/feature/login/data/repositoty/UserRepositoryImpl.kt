package com.example.solutionx.feature.login.data.repositoty


import com.example.solutionx.feature.login.data.mappers.UserMapper
import com.example.solutionx.feature.login.domain.models.User
import com.example.solutionx.feature.login.domain.remote.RemoteDataSource
import com.example.solutionx.feature.login.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {
    override suspend fun loginWithPhone(phone: String): User {
        val userDto = remoteDataSource.loginWithPhone(phone)
        return UserMapper.mapToDomain(userDto)
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): User {
        val userDto = remoteDataSource.loginWithEmailPassword(email, password)
        return UserMapper.mapToDomain(userDto)
    }

    override suspend fun loginWithSocial(token: String): User {
        val userDto = remoteDataSource.loginWithSocial(token)
        return UserMapper.mapToDomain(userDto)
    }
}