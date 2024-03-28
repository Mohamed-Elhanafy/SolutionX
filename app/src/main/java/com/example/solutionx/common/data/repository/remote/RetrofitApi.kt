package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponseDto



}