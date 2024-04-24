package com.example.solutionx.features.saveList.data.repository

import com.example.solutionx.features.saveList.domain.repository.local.ILocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SaveListRepositoryTest{
    private lateinit var localDataSource: ILocalDataSource
    private lateinit var saveListRepository: SaveListRepository

    @Before
    fun setUp() {
        localDataSource = mock()
        saveListRepository = SaveListRepository(localDataSource)
    }


    @Test
    fun `saveNamesList() with valid data then save data in localDataSource`() = runTest {

        val names = listOf("name1", "name2", "name3")

        saveListRepository.saveNamesList(names)

        verify(localDataSource).saveNamesList(names)
    }

    @Test
    fun `getNamesList() with valid data then return data from localDataSource`() = runTest {

        val names = listOf("name1", "name2", "name3")

        whenever(localDataSource.getNamesList()).thenReturn(names)

        val result = saveListRepository.getNamesList()

        assertEquals(names, result)
    }

    @Test
    fun `saveNamesList() with empty list then do not save data in localDataSource`() = runTest {

        val names = emptyList<String>()

        saveListRepository.saveNamesList(names)

        verify(localDataSource, never()).saveNamesList(any())
    }

    @Test
    fun `getNamesList() with no stored data then return empty list`() = runTest {

        val names = emptyList<String>()

        whenever(localDataSource.getNamesList()).thenReturn(names)

        val result = saveListRepository.getNamesList()

        assertEquals(names, result)
    }
}