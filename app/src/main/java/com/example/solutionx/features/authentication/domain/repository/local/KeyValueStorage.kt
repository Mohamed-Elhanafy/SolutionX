package com.example.solutionx.features.authentication.domain.repository.local

internal interface KeyValueStorage {
    suspend fun <T> save(key: String, value: T)
    suspend fun <T> get(key: String): T
}