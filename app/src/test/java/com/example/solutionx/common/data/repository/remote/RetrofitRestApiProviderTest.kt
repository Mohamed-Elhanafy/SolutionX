package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.dto.UserDto
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response
import java.lang.reflect.Type

class RetrofitRestApiProviderTest {
    private lateinit var retrofitApi: RetrofitApi
    private lateinit var retrofitRestApiProvider: IRestApiNetworkProvider

    @Before
    fun setup() {
        // Create a mock RetrofitApi
        retrofitApi = Mockito.mock(RetrofitApi::class.java)
        // Initialize the RetrofitRestApiProvider with the mock RetrofitApi
        retrofitRestApiProvider = RetrofitRestApiProvider(retrofitApi)
    }



}