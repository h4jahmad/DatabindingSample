package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.ui.LoginUiState

interface LoginUseCase {
    fun login(email: String, password: String): LoginUiState
}