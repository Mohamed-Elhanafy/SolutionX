package com.example.solutionx.features.saveList.domain.repository

interface ISaveListRepository {
    suspend fun saveNamesList(names: List<String>)
    suspend fun getNamesList(): List<String>

}