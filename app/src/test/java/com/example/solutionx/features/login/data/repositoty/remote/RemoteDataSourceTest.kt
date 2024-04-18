package com.example.solutionx.features.login.data.repositoty.remote


import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.PhoneRequest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest {

    private lateinit var provider: FakeRestApiNetworkProvider
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        provider = FakeRestApiNetworkProvider()
        remoteDataSource = RemoteDataSource(provider)
    }

    @Test
    fun loginWithPhoneReturnsExpectedResult() = runTest {
        val loginRequest = LoginRequest(PhoneRequest("002","100100100"), "testCode")
        val expectedResponse = LoginResponseDto("testToken", null)

        provider.postResponse = expectedResponse

        val result = remoteDataSource.loginWithPhone(loginRequest)

        assertEquals(expectedResponse, result)
    }


    @Test
    fun loginWithPhoneReturnsNullWhenProviderReturnsNull() = runTest {
        val loginRequest = LoginRequest(PhoneRequest("002","100100100"), "testCode")
        val expectedResponse = null

        provider.postResponse = expectedResponse

        val result = remoteDataSource.loginWithPhone(loginRequest)

        assertEquals(expectedResponse, result)
    }




}