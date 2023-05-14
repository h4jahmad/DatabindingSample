package com.example.databindingsample.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingsample.home.ui.models.ImageListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _imageInfo = MutableLiveData<ImageListItem>()
    val imageInfo: LiveData<ImageListItem>
        get() = _imageInfo

    private val _imageHeight = MutableLiveData(0)
    val imageHeight: LiveData<Int>
        get() = _imageHeight

    fun setImageInfo(info: ImageListItem) {
        _imageInfo.value = info
    }

    fun setImageHeight(height: Int) {
        _imageHeight.value = height
    }
}