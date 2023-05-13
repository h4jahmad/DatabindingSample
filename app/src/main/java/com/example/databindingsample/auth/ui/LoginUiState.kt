package com.example.databindingsample.auth.ui

sealed interface LoginUiState {
    object LoginSuccess: LoginUiState
    object WrongCreds: LoginUiState
    object InvalidEmail: LoginUiState
    object InvalidPassword: LoginUiState
}