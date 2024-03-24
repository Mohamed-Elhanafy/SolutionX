package com.example.solutionx.common.data.models

sealed class Resource<out Model> {
    class Success<out Model>(val data: Model) : Resource<Model>()
    class Failure(val exception: SolutionXException) : Resource<Nothing>()
    class Loading(val isLoading :Boolean) : Resource<Nothing>()

}