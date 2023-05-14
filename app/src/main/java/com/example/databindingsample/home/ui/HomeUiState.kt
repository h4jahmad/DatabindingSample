package com.example.databindingsample.home.ui

import com.example.databindingsample.home.ui.models.ImageListItem

data class HomeUiState(
    val errorMessage: String? = null,
    val errorMessageResId: Int? = null,
    val shouldShowSnackbar: Boolean = false,
    val shouldShowImageList: Boolean = false,
    val imageList: List<ImageListItem> = emptyList(),
    val shouldShowProgress: Boolean = false,
)