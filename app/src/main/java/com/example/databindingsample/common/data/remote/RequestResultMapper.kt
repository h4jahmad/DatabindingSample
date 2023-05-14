package com.example.databindingsample.common.data.remote

import com.example.databindingsample.common.RequestException
import com.example.databindingsample.common.RequestResult
import retrofit2.Response

fun mapException(t: Throwable): RequestResult<Nothing> = RequestResult.Failure(t)

fun <T> mapResponse(response: Response<T>): RequestResult<T> = when {
    response.isSuccessful -> RequestResult.Success(response)
    response.code() == 429 -> RequestResult.Error(
        RequestException("Too many requests. Hold on for a while.")
    )
    else -> RequestResult.Error(
        RequestException("Server error: ${response.code()}")
    )
}