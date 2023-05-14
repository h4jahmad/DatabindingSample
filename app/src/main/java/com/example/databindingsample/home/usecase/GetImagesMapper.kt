package com.example.databindingsample.home.usecase

import com.example.databindingsample.entities.Hits
import com.example.databindingsample.home.ui.models.ImageListItem

object GetImagesMapper {
    fun map(oldItem: Hits): ImageListItem = with(oldItem) {
        ImageListItem(
            id,
            previewURL,
            largeImageURL,
            imageWidth,
            imageHeight,
            type,
            tags,
            views,
            likes,
            comments,
            collections,
            downloads,
            user,
            userImageURL
        )
    }
}