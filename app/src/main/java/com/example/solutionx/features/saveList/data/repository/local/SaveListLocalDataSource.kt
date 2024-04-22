package com.example.solutionx.features.saveList.data.repository.local

import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.saveList.domain.repository.local.ISaveListLocalDataSource
import com.google.gson.Gson
import javax.inject.Inject

internal class SaveListLocalDataSource @Inject constructor (
    private val keyValueStorage: KeyValueStorage,
    private val gson: Gson
) : ISaveListLocalDataSource {
    override suspend fun saveNamesList(names: List<String>) {
        keyValueStorage.save(NAMES_LIST, gson.toJson(names))
    }

    override suspend fun getNamesList(): List<String> {
        val namesJson = keyValueStorage.get<String, String>(NAMES_LIST)
        return gson.fromJson(namesJson, Array<String>::class.java).toList()
    }

    companion object {
        private const val NAMES_LIST = "NAMES_LIST"
    }
}