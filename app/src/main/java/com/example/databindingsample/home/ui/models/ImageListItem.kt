package com.example.databindingsample.home.ui.models

data class ImageListItem(
    val id: Int,
    val thumbnailUrl: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val type: String,
    val tags: String,
    val viewsCount: Int,
    val likesCount: Int,
    val commentsCount: Int,
    val favorites: Int,
    val downloadsCount: Int,
    val userName: String,
    val userImageUrl: String
)
