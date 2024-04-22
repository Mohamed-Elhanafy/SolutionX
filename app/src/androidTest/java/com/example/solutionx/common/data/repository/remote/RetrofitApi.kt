package com.example.solutionx.common.data.repository.remote

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiTest{
    private lateinit var server: MockWebServer//Fake server from square lib
    private lateinit var api: RetrofitApi


    @Before//Using JUnit5
    fun beforeEach() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))//Pass any base url like this
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitApi::class.java)
    }
    @After
    fun afterEach() {
        server.shutdown()
    }


    // todo add test for post method
}