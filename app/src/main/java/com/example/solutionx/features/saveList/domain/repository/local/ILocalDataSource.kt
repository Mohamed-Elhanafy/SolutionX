package com.example.solutionx.features.saveList.domain.repository.local

internal interface ISaveListLocalDataSource {

    suspend fun saveNamesList(names: List<String>)
    suspend fun getNamesList(): List<String>

}