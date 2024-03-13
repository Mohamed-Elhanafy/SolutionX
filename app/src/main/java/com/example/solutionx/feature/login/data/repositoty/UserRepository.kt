package com.example.solutionx.feature.login.data.repositoty


import com.example.solutionx.feature.login.data.mappers.UserMapper
import com.example.solutionx.feature.login.data.local.LocalDataSourceImpl
import com.example.solutionx.feature.login.data.remote.RemoteDataSourceImpl
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.remote.RemoteDataSource
import com.example.solutionx.feature.login.domain.models.User
import com.example.solutionx.feature.login.domain.repository.UserRepositoryInterface

class UserRepository(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl,
) : UserRepositoryInterface {
    override suspend fun loginWithPhone(phone: String): User {
        val userDto = remoteDataSource.loginWithPhone(phone)
        val userEntity = UserMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return UserMapper.mapToDomain(userDto)
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): User {
        val userDto = remoteDataSource.loginWithEmailPassword(email, password)
        val userEntity = UserMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return UserMapper.mapToDomain(userDto)
    }

    override suspend fun loginWithSocial(token: String): User {
        val userDto = remoteDataSource.loginWithSocial(token)
        val userEntity = UserMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return UserMapper.mapToDomain(userDto)
    }
}