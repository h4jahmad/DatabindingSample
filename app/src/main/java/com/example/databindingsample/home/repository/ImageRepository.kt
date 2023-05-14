package com.example.databindingsample.home.repository

import com.example.databindingsample.common.RequestResult
import com.example.databindingsample.entities.ImagesResponse

interface ImageRepository {
    suspend fun getImages(): RequestResult<ImagesResponse>
}