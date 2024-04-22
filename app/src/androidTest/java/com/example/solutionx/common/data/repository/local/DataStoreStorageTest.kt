package com.example.solutionx.common.data.repository.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



//todo fix naming convention in this class

@RunWith(AndroidJUnit4::class)
class DataStoreStorageAndroidTest {
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
        dataStoreStorage = DataStoreStorage.getInstance(context)
    }
    private lateinit var dataStoreStorage: DataStoreStorage

    lateinit var context: Context


    @Test
    fun testSaveAndGet() {
        runBlocking {

            val key = "testKey"
            val value = "testValue"

            dataStoreStorage.save(key, value)
            val retrievedValue: String = dataStoreStorage.get(key)
            assertEquals(value, retrievedValue)
        }
    }
    @Test
    fun testSaveWithSameKey() {
        runBlocking {
            val key = "testKey"
            val value1 = "testValue1"

            dataStoreStorage.save(key, value1)
            val retrievedValue1: String = dataStoreStorage.get(key)
            assertEquals(value1, retrievedValue1)

            val value2 = "testValue2"
            dataStoreStorage.save(key, value2)
            val retrievedValue2: String = dataStoreStorage.get(key)

            assertEquals(value2, retrievedValue2)
        }
    }
    @Test(expected = IllegalArgumentException::class)
    fun testSaveAndGetWithNonExistentKey() {
        runBlocking {
            val nonExistentKey = "nonExistentKey"
            dataStoreStorage.get<String, String>(nonExistentKey)
        }
    }
    @Test
    fun testSaveAndGetWithDifferentTypes() {
        runBlocking {
            val stringKey = "stringKey"
            val stringValue = "testValue"
            dataStoreStorage.save(stringKey, stringValue)
            val retrievedStringValue: String = dataStoreStorage.get(stringKey)
            assertEquals(stringValue, retrievedStringValue)

            val intKey = "intKey"
            val intValue = 123
            dataStoreStorage.save(intKey, intValue)
            val retrievedIntValue: Int = dataStoreStorage.get(intKey)
            assertEquals(intValue, retrievedIntValue)

            val booleanKey = "booleanKey"
            val booleanValue = true
            dataStoreStorage.save(booleanKey, booleanValue)
            val retrievedBooleanValue: Boolean = dataStoreStorage.get(booleanKey)
            assertEquals(booleanValue, retrievedBooleanValue)
        }
    }
    @Test(expected = IllegalArgumentException::class)
    fun testSaveWithUnsupportedType() {
        runBlocking {
            val key = "unsupportedKey"
            val unsupportedValue = 1.0
            dataStoreStorage.save(key, unsupportedValue)
        }
    }



}