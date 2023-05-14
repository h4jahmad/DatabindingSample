package com.example.databindingsample.home.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databindingsample.R
import com.example.databindingsample.home.usecase.GetImagesResult
import com.example.databindingsample.home.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        setLoading()
        viewModelScope.launch {
            when (val result = getImagesUseCase()) {
                is GetImagesResult.ImagesReceived -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = null,
                            errorMessageResId = null,
                            shouldShowSnackbar = false,
                            shouldShowProgress = false,
                            shouldShowImageList = true,
                            imageList = result.list
                        )
                    }
                }

                is GetImagesResult.ServerErrorReceived -> {
                    setError(
                        result.e.message
                            ?: throw IllegalArgumentException("error message has not been fed.")
                    )
                }

                is GetImagesResult.RequestFailure -> {
                    setError(R.string.network_connection_error)
                }
            }
        }
    }

    private fun setLoading() {
        _uiState.update {
            it.copy(
                shouldShowSnackbar = false,
                shouldShowImageList = false,
                shouldShowProgress = true
            )
        }
    }

    private fun setError(error: String) {
        _uiState.update {
            it.copy(
                errorMessage = error,
                errorMessageResId = null,
                shouldShowSnackbar = true,
                shouldShowProgress = false,
            )
        }
    }

    private fun setError(@StringRes errorResId: Int) {
        _uiState.update {
            it.copy(
                errorMessageResId = errorResId,
                errorMessage = null,
                shouldShowSnackbar = true,
                shouldShowProgress = false,
            )
        }
    }

    fun setErrorDismissed() {
        _uiState.update {
            it.copy(
                errorMessageResId = null,
                shouldShowSnackbar = false,
            )
        }
    }
}