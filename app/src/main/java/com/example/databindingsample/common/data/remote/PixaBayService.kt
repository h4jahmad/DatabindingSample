package com.example.databindingsample.common.data.remote

import com.example.databindingsample.BuildConfig
import com.example.databindingsample.common.RequestResult
import com.example.databindingsample.entities.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayService {
    @GET(".")
    suspend fun getImages(
        @Query("key") key: String = BuildConfig.PIXABAY_API_KEY
    ): RequestResult<ImagesResponse>
}