package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.data.models.SolutionXException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class StatusCodeErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            if (!response.isSuccessful) {
                when (response.code) {
                    400 -> throw SolutionXException.HttpException("Bad Request")
                    401 -> throw SolutionXException.HttpException("Unauthorized")
                    403 -> throw SolutionXException.HttpException("Forbidden")
                    404 -> throw SolutionXException.HttpException("Not Found")
                    else -> throw SolutionXException.Unknown("Unknown error")
                }
            }
            return response
        } catch (e: IOException) {
            throw SolutionXException.IOException("IO Exception: ${e.message}")
        }
    }
}