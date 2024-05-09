package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginResponseDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RetrofitApi {

    @POST("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun  post(
        @Path("pathUrl") pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParameters: Map<String, Any>,
        @Body requestBody: Any
    ): ResponseBody


    @GET("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun get(
        @Path("pathUrl") pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParameters: Map<String, Any>
    ): Response<ResponseBody>


    @PUT
    @JvmSuppressWildcards
    suspend fun put(
        @Path("pathUrl") pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParameters: Map<String, Any>,
        @Body requestBody: Any
    ): ResponseBody


    @DELETE("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun delete(
        @Path("pathUrl") pathUrl: String,
        @HeaderMap headers: Map<String, Any>,
        @QueryMap queryParameters: Map<String, Any>,
        @Body requestBody: Any
    ): ResponseBody


}