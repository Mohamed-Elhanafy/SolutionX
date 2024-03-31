package com.example.solutionx.common.data.models


sealed class SolutionXException(message: String? = null) : Exception(message) {
    class NoNetworkConnection(message: String? = "No network connection") :
        SolutionXException(message)

    class HttpException(message: String? = "HTTP Exception") : SolutionXException(message)
    class IOException(message: String? = "IO Exception") : SolutionXException(message)

    sealed class NetworkErrors(message: String? = null) : SolutionXException(message) {
        class NoInternetConnection(message: String? = "No internet connection") : NetworkErrors(message)
        class TimeoutException(message: String? = "Network timeout") : NetworkErrors(message)
        class UnreachableNetwork(message: String? = "Network is unreachable") : NetworkErrors(message)
    }
    data class Unknown(override val message: String?) : SolutionXException(message)

}