package com.example.solutionx.common


sealed class SolutionXException(message: String? = null) : Exception(message) {
    class NoNetworkConnection(message: String? = "No network connection") :
        SolutionXException(message)

    class HttpException(message: String? = "HTTP Exception") : SolutionXException(message)
    class IOException(message: String? = "IO Exception") : SolutionXException(message)
}