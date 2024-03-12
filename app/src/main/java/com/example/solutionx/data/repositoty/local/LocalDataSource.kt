package com.example.solutionx.data.repositoty.local

import com.example.solutionx.data.model.entity.UserEntity

interface LocalDataSource {
    fun saveUser(userEntity: UserEntity)

    fun getUser(userId: String): UserEntity
}