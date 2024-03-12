package com.example.solutionx.data.repositoty


import com.example.solutionx.data.mappers.UserMapper
import com.example.solutionx.data.model.dto.UserDto
import com.example.solutionx.data.repositoty.local.LocalDataSource
import com.example.solutionx.data.repositoty.remote.RemoteDataSource
import com.example.solutionx.domain.models.User

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val userMapper: UserMapper
) {
    suspend fun loginWithPhone(phone: String): User {
        val userDto = remoteDataSource.loginWithPhone(phone)
        val userEntity = userMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return userMapper.mapToDomain(userDto)
    }

    suspend fun loginWithEmailPassword(email: String, password: String): User {
        val userDto = remoteDataSource.loginWithEmailPassword(email, password)
        val userEntity = userMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return userMapper.mapToDomain(userDto)
    }

    suspend fun loginWithSocial(token: String): User {
        val userDto = remoteDataSource.loginWithSocial(token)
        val userEntity = userMapper.mapToEntity(userDto)
        localDataSource.saveUser(userEntity)
        return userMapper.mapToDomain(userDto)
    }
}
