package com.example.databindingsample.home.repository

import com.example.databindingsample.common.RequestResult
import com.example.databindingsample.common.data.remote.PixaBayService
import com.example.databindingsample.entities.ImagesResponse
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(
    private val service: PixaBayService,
): ImageRepository {

    override suspend fun getImages():RequestResult<ImagesResponse> = service.getImages()

}