package com.example.solutionx.common.domain.repository.remote

import java.lang.reflect.Type

interface IRestApiNetworkProvider {

    suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParameters: Map<String, Any>? = null,
    ): ResponseBody


    suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParameters: Map<String, Any>? = null,
        requestBody: RequestBody? = null
    ): ResponseBody


    suspend fun <ResponseBody, RequestBody> put(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParameters: Map<String, Any>? = null,
        requestBody: RequestBody? = null
    ): ResponseBody


    suspend fun <ResponseBody, RequestBody> delete(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody
}