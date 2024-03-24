package com.example.solutionx.features.authentication.data.repositoty


import com.example.solutionx.features.authentication.data.mappers.UserMapper
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.models.User
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import com.example.solutionx.features.authentication.domain.repository.UserRepository
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
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

    override suspend fun saveUser(user: UserEntity) {
        localDataSource.saveUser(user)
    }
}