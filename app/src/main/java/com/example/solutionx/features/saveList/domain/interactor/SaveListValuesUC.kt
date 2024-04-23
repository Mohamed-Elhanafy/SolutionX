package com.example.solutionx.features.saveList.domain.interactor

import com.example.solutionx.common.data.extentions.wrapper
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.saveList.domain.repository.ISaveListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveListValuesUC @Inject constructor(
    private val saveListRepository: ISaveListRepository
) {

    suspend fun saveNamesList(names: List<String>) {
            saveListRepository.saveNamesList(names)
    }


    suspend fun getNamesList(): Flow<Resource<List<String>>> {
        return wrapper {
            saveListRepository.getNamesList()
        }
    }


}