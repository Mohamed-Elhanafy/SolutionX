package com.example.solutionx.feature.login.data.repositoty.local

import com.example.solutionx.feature.login.data.model.entity.UserEntity

interface LocalDataSource {
    fun saveUser(userEntity: UserEntity)

    fun getUser(userId: String): UserEntity
}