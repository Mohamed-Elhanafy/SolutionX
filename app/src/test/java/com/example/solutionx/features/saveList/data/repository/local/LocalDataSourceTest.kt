package com.example.solutionx.features.saveList.data.repository.local

import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.never

class LocalDataSourceTest {

    private lateinit var keyValueStorage: KeyValueStorage
    private lateinit var gson: Gson
    private lateinit var localDataSource: LocalDataSource


    @Before
    fun setUp() {
        keyValueStorage = mock()
        gson = Gson()
        localDataSource = LocalDataSource(keyValueStorage, gson)
    }


    @Test
    fun `saveNamesList() with valid data then save data in storage`() = runTest {
        // Arrange
        val names = listOf("name1", "name2", "name3")
        val namesJson = gson.toJson(names)

        // Act
        localDataSource.saveNamesList(names)

        // Assert
        verify(keyValueStorage).save<String, String>("names_key", namesJson)
    }

    @Test
    fun `getNamesList() with valid data then return data from storage`() = runTest {
        val names = listOf("name1", "name2", "name3")
        val namesJson = gson.toJson(names)

        whenever(keyValueStorage.get<String, String>(any())).thenReturn(namesJson)

        val result = localDataSource.getNamesList()

        assertEquals(names, result)
    }

    @Test
    fun `saveNamesList() with empty list then return empty list`() = runTest {
        whenever(keyValueStorage.get<String, String>(any())).thenReturn(null)

        localDataSource.saveNamesList(emptyList())

        verify(keyValueStorage, never()).save<String, String>(any(), any())
    }

    @Test
    fun `getNamesList() with empty list then return empty list`() = runTest {
        whenever(keyValueStorage.get<String, String>(any())).thenReturn("[]")
        val result = localDataSource.getNamesList()

        assertEquals(emptyList<String>(), result)
    }


    @Test
    fun `saveNamesList() with list containing empty strings then save data in storage`() = runTest {
        // Arrange
        val names = listOf("mohamed", "", "")
        val namesJson = gson.toJson(names)

        // Act
        localDataSource.saveNamesList(names)

        // Assert
        verify(keyValueStorage).save<String, String>("names_key", namesJson)
    }

    @Test(expected = JsonSyntaxException::class)
    fun `getNamesList() with invalid data then throw JsonSyntaxException`() = runTest {
        // Arrange
        val invalidJson = "{not a valid list}"

        whenever(keyValueStorage.get<String, String>(any())).thenReturn(invalidJson)

        localDataSource.getNamesList()


    }

}