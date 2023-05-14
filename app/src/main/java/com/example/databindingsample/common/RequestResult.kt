package com.example.databindingsample.common

import retrofit2.Response

sealed interface RequestResult<out T> {
    data class Success<T>(val data: Response<T>) : RequestResult<T>
    data class Error(val e: RequestException) : RequestResult<Nothing>
    data class Failure(val e: Throwable) : RequestResult<Nothing>
}