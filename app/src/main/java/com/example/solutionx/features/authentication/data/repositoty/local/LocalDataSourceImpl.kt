package com.example.solutionx.features.authentication.data.repositoty.local

import android.util.Log
import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource

internal class LocalDataSourceImpl : LocalDataSource {


    override fun saveUser(loginResponse: LoginResponseEntity) {
        Log.d("LocalDataSourceImpl", "saveUser: $loginResponse")
    }

    override fun getUser(userId: String): UserEntity {
        return UserEntity(-1 ,"", "" )
    }


}