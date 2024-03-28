package com.example.solutionx.common.data.extentions

import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.common.data.models.SolutionXException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <ResponseType> wrapper(networkCall: suspend () -> ResponseType): Flow<Resource<ResponseType>> = flow {
    try {
        emit(Resource.Loading(true))

        val result = networkCall()
        emit(Resource.Success(result))
    } catch (e: Exception) {
        val failureResource = if (e is SolutionXException)
            e
        else
            SolutionXException.Unknown(message = "Unknown error $e")

        emit(Resource.Failure(failureResource))
    }
}