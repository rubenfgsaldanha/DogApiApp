package com.example.dogapiapp.data.remote

sealed class ApiResult<out T : Any?> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val errorMessage: String) : ApiResult<Nothing>()
}
