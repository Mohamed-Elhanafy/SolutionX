package com.example.solutionx.features.authentication.domain.repository.local

import com.example.solutionx.features.authentication.data.model.entity.UserEntity

internal interface LocalDataSource {
    fun saveUser(userEntity: UserEntity)

    fun getUser(userId: String): UserEntity
}