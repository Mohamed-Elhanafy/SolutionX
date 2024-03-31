package com.example.solutionx.common.domain.repository.local

internal interface KeyValueStorage {
    suspend fun <K, V> save(key: K, value: V)
    suspend fun <K, V> get(key: K): V
}