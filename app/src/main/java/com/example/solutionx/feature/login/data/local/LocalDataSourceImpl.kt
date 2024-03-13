package com.example.solutionx.feature.login.data.local

import com.example.solutionx.feature.login.data.model.entity.UserEntity
import com.example.solutionx.feature.login.domain.local.LocalDataSource

class LocalDataSourceImpl : LocalDataSource {
    override fun saveUser(userEntity: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun getUser(userId: String): UserEntity {
        TODO("Not yet implemented")
    }
}