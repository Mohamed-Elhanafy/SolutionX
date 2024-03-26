package com.example.solutionx.common.data.extentions

import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.common.data.models.SolutionXException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

suspend fun <ResponseType> executeNetworkCall(networkCall: suspend () -> ResponseType): Flow<Resource<ResponseType>> = flow {
    try {
        withContext(Dispatchers.IO) {
            emit(Resource.Loading(true))

            val result = networkCall()
            emit(Resource.Success(result))
        }
    } catch (e: Exception) {
        withContext(Dispatchers.IO) {
            val failureResource = if (e is SolutionXException)
                e
            else
                SolutionXException.Unknown(message = "Unknown error $e")

            emit(Resource.Failure(failureResource))
        }
    }
}