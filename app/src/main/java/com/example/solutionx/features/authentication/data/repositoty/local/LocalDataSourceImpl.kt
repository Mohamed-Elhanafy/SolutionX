package com.example.solutionx.features.authentication.data.repositoty.local

import android.util.Log
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import com.example.solutionx.features.authentication.domain.models.enums.Gender

internal class LocalDataSourceImpl : LocalDataSource {
    override fun saveUser(userEntity: UserEntity) {
        Log.i("LocalDataSourceImpl", "saveUser:  $userEntity")
    }

    override fun getUser(userId: String): UserEntity {
        return UserEntity("1", "Ahmed ",Gender.MALE)
    }
}