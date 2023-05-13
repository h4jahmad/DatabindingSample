package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.ui.LoginUiState

interface LoginUseCase {
    operator fun invoke(email: String, password: String): LoginUiState
}