package com.example.solutionx.feature.login.data.local

import android.util.Log
import com.example.solutionx.feature.login.data.model.entity.UserEntity
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.models.enums.Gender

class LocalDataSourceImpl : LocalDataSource {
    override fun saveUser(userEntity: UserEntity) {
        Log.i("LocalDataSourceImpl", "saveUser:  $userEntity")
    }

    override fun getUser(userId: String): UserEntity {
        return UserEntity("1", "Ahmed ",Gender.MALE)
    }
}