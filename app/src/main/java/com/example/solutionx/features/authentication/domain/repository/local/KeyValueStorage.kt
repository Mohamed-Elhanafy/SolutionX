package com.example.solutionx.features.authentication.domain.repository.local

internal interface KeyValueStorage {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String): String
    suspend fun saveInt(key: String, value: Int)
    suspend fun getInt(key: String): Int
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean
}