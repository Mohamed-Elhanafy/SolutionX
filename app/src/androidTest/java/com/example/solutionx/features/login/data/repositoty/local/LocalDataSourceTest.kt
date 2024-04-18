package com.example.solutionx.features.login.data.repositoty.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.login.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.di.LoginDiModule.provideDataStoreStorage
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class LocalDataSourceTest {

    private lateinit var keyValueStorage: KeyValueStorage
    private lateinit var gson: Gson
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        keyValueStorage = provideDataStoreStorage(context) // Use provided instance
        gson = Gson()
        localDataSource = LocalDataSource(keyValueStorage, gson)
    }

    @Test
    fun saveLoginSavesEncryptedLoginData() = runTest {
        val loginResponse = LoginResponseEntity("testToken", UserEntity(1, "testEmail", "testPhone"))

        localDataSource.saveLogin(loginResponse)

        val savedUser = keyValueStorage.get<String, String>(LocalDataSource.USER_ENCRYPTED)
        val savedUserIv = keyValueStorage.get<String, String>(LocalDataSource.USER_IV)
        val savedAccessToken = keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_ENCRYPT)
        val savedAccessTokenIv = keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_IV)

        assertNotNull(savedUser)
        assertNotNull(savedUserIv)
        assertNotNull(savedAccessToken)
        assertNotNull(savedAccessTokenIv)
    }

    @Test
    fun getAccessTokenReturnsDecryptedAccessToken() = runTest {
        val expectedToken = "testToken"
        val loginResponse = LoginResponseEntity(expectedToken, UserEntity(1, "testEmail", "testPhone"))
        localDataSource.saveLogin(loginResponse)

        val actualToken = localDataSource.getAccessToken()

        assertEquals(expectedToken, actualToken)
    }

    @Test
    fun getUserReturnsDecryptedUserData() = runTest {
        val expectedUser = UserEntity(1, "testEmail", "testPhone")
        val loginResponse = LoginResponseEntity("testToken", expectedUser)
        localDataSource.saveLogin(loginResponse)

        val actualUser = localDataSource.getUser()

        assertEquals(expectedUser, actualUser)
    }


    @Test(expected = Exception::class)
    fun saveLoginThrowsExceptionWhenEncryptionFails() = runTest {
        val brokenGson = mock(Gson::class.java)
        `when`(brokenGson.toJson(any())).thenThrow(RuntimeException::class.java)
        val localDataSourceWithBrokenGson = LocalDataSource(keyValueStorage, brokenGson)
        val loginResponse = LoginResponseEntity("testToken", UserEntity(1, "testEmail", "testPhone"))

        localDataSourceWithBrokenGson.saveLogin(loginResponse)

    }

}