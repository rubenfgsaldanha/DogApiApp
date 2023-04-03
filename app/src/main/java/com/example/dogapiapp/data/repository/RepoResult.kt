package com.example.dogapiapp.data.repository

sealed class RepoResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : RepoResult<T>()
    data class Saved<out T : Any>(val data: T) : RepoResult<T>()
    data class Error(val errorMessage : String) : RepoResult<Nothing>()
}
