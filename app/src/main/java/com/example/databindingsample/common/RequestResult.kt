package com.example.databindingsample.common

sealed interface RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>
    data class Error(val e: RequestException) : RequestResult<Nothing>
    data class Failure(val e: Throwable) : RequestResult<Nothing>
}