package com.example.solutionx.features.saveList.data.repository.local

import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.saveList.domain.repository.local.ILocalDataSource
import com.google.gson.Gson
import javax.inject.Inject

internal class LocalDataSource @Inject constructor(
    private val keyValueStorage: KeyValueStorage,
    private val gson: Gson
) : ILocalDataSource {
    override suspend fun saveNamesList(names: List<String>) {
        if (names.isNotEmpty()) {
            keyValueStorage.save(NAMES_LIST, Gson().toJson(names))
        }
    }

    override suspend fun getNamesList(): List<String> {
        val namesJson = keyValueStorage.get<String, String>(NAMES_LIST)
        return gson.fromJson(namesJson, Array<String>::class.java).toList()
    }

    companion object {
        private const val NAMES_LIST = "NAMES_LIST"
    }
}