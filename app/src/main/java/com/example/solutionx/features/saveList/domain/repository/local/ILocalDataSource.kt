package com.example.solutionx.features.saveList.domain.repository.local

 interface ILocalDataSource {

    suspend fun saveNamesList(names: List<String>)
    suspend fun getNamesList(): List<String>

}