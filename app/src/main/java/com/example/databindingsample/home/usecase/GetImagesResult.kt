package com.example.databindingsample.home.usecase

import com.example.databindingsample.home.ui.models.ImageListItem

sealed interface GetImagesResult {
    data class ImagesReceived(val list: List<ImageListItem>) : GetImagesResult
    data class ServerErrorReceived(val e: Exception) : GetImagesResult
    data class RequestFailure(val e: Throwable) : GetImagesResult
}