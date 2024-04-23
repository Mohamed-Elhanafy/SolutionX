package com.example.solutionx.features.saveList.data.repository

import com.example.solutionx.features.saveList.domain.repository.ISaveListRepository
import com.example.solutionx.features.saveList.domain.repository.local.ILocalDataSource
import javax.inject.Inject

internal class SaveListRepository @Inject constructor (
    private val localDataSource: ILocalDataSource
) : ISaveListRepository {
    override suspend fun saveNamesList(names: List<String>) {
        localDataSource.saveNamesList(names)
    }

    override suspend fun getNamesList(): List<String> {
        return localDataSource.getNamesList()
    }

}