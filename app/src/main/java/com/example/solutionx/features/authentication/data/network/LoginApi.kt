package com.example.solutionx.features.authentication.data.network

import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("phone[country_code]") countryCode: String,
        @Field("phone[number]") phoneNumber: String,
        @Field("password") password: String
    ): LoginResponseDto


}