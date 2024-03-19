package com.example.solutionx.common


sealed class SolutionXException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    class NoNetworkConnection(message: String? = "No network connection", cause: Throwable? = null) : SolutionXException(message, cause)
    class HttpException(message: String? = "HTTP Exception", cause: Throwable? = null) : SolutionXException(message, cause)
    class IOException(message: String? = "IO Exception", cause: Throwable? = null) : SolutionXException(message, cause)
}