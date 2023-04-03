package com.example.dogapiapp.data.remote

import retrofit2.Response

open class ApiPerformer< T : Any> {

    fun getResult(response: Response<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            val body = response.body() ?: return ApiResult.Error("response body was null")
            return ApiResult.Success(body)
        } else {
            ApiResult.Error(response.errorBody()?.toString() ?: "Error making api call")
        }
    }
}
