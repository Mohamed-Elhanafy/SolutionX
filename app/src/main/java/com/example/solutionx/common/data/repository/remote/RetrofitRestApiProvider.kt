package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.google.gson.Gson
import java.lang.reflect.Type

class RetrofitRestApiProvider(private val api: RetrofitApi) : IRestApiNetworkProvider {
    override suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParameters: Map<String, Any>?
    ): ResponseBody {
        val response = api.get(
            pathUrl = pathUrl,
            headers = headers ?: emptyMap(),
            queryParameters = queryParameters ?: emptyMap()
        )
        return Gson().fromJson(response.body().toString(), responseWrappedModel)
    }

    override suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParameters: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        val response = api.post(
            pathUrl = pathUrl,
            headers = headers ?: emptyMap(),
            queryParameters = queryParameters ?: emptyMap(),
            requestBody = requestBody ?: Unit
        )
        return Gson().fromJson(response.string(), responseWrappedModel)
    }

    override suspend fun <ResponseBody, RequestBody> delete(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>?,
        queryParams: Map<String, Any>?, requestBody: RequestBody?
    ): ResponseBody {
        val response = api.delete(
            pathUrl = pathUrl,
            headers = headers ?: emptyMap(),
            queryParameters = queryParams ?: emptyMap(),
            requestBody = requestBody ?: Unit
        )
        return Gson().fromJson(response.string(), responseWrappedModel)
    }


    override suspend fun <ResponseBody, RequestBody> put(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParameters: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
           val response = api.put(
                pathUrl = pathUrl,
                headers = headers ?: emptyMap(),
                queryParameters = queryParameters ?: emptyMap(),
                requestBody = requestBody ?: Unit
            )
            return Gson().fromJson(response.string(), responseWrappedModel)
    }


}