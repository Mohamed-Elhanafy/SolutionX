package com.example.solutionx.features.login.data.repositoty.local

import com.example.solutionx.android.helpers.security.IKeystoreUtils
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.login.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalDataSourceTest {


    private lateinit var keyValueStorage: KeyValueStorage
    private lateinit var gson: Gson
    private lateinit var keystoreUtils: IKeystoreUtils
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        keyValueStorage = mock()
        gson = Gson()
        keystoreUtils = mock()
        localDataSource = LocalDataSource(keyValueStorage, gson, keystoreUtils)
    }

    @Test
    fun saveLoginSavesEncryptedLoginData() = runTest {
        val loginResponse =
            LoginResponseEntity("testToken", UserEntity(1, "testEmail", "testPhone"))

        whenever(keystoreUtils.encrypt(any())).thenReturn(Pair(ByteArray(0), ByteArray(0)))

        localDataSource.saveLogin(loginResponse)

        verify(keyValueStorage).save(LocalDataSource.USER_ENCRYPTED, "")
        verify(keyValueStorage).save(LocalDataSource.USER_IV, "")
        verify(keyValueStorage).save(LocalDataSource.ACCESS_TOKEN_ENCRYPT, "")
        verify(keyValueStorage).save(LocalDataSource.ACCESS_TOKEN_IV, "")
        verify(keyValueStorage).save(LocalDataSource.IS_USER_LOGGED_IN, "true")
    }


}