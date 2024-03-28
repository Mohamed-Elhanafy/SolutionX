package com.example.solutionx.common.domain.repository.local

internal interface KeyValueStorage {
    suspend fun <T> save(key: String, value: T)
    suspend fun <T> get(key: String): T
}