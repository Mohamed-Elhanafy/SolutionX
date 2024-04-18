package com.example.solutionx.features.login.domain.interactor


import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.PhoneRequest
import com.example.solutionx.features.login.data.repositoty.FakeLoginRepository
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.login.domain.models.User
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class LoginWithPhoneUCTest {
    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var loginWithPhoneUC: LoginWithPhoneUC

    @Before
    fun setup() {
        loginRepository = FakeLoginRepository()
        loginWithPhoneUC = LoginWithPhoneUC(loginRepository)
    }

    @Test
    fun invokeCallsRepositoryMethodsAndReturnsExpectedResult() = runTest {
        val loginRequest = LoginRequest(PhoneRequest("002","01110964776"), "testPassword")
        val expectedLoginResponse = LoginResponse("testToken", User(1, "testEmail", "testPhone"))
        loginRepository.loginResponse = expectedLoginResponse

        val result = mutableListOf<Resource<LoginResponse>>()
        loginWithPhoneUC.invoke(loginRequest).collect { result.add(it) }

        val lastResult = result.last()
        if (lastResult is Resource.Success) {
            assertEquals(expectedLoginResponse, lastResult.data)
        } else {
            fail("Expected Resource.Success, but was $lastResult")
        }
    }

    @Test
    fun invokeReturnsFailureWhenLoginFailsDueToIncorrectCredentials() = runTest {

        val loginRequest = LoginRequest(PhoneRequest("002","01110964776"), "wrongPassword")
        loginRepository.loginResponse = null // Simulate login failure


        val result = mutableListOf<Resource<LoginResponse>>()
        loginWithPhoneUC.invoke(loginRequest).collect { result.add(it) }


        val lastResult = result.last()
        assertTrue(lastResult is Resource.Failure)
    }




    @Test
    fun invokeReturnsFailureWhenThereIsANetworkError() = runTest {

        val loginRequest = LoginRequest(PhoneRequest("002","01110964776"), "testPassword")
        loginRepository.loginResponse = null // Simulate network error


        val result = mutableListOf<Resource<LoginResponse>>()
        loginWithPhoneUC.invoke(loginRequest).collect { result.add(it) }


        val lastResult = result.last()
        assertTrue(lastResult is Resource.Failure)
    }


}