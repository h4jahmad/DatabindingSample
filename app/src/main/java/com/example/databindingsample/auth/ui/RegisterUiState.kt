package com.example.databindingsample.auth.ui

sealed interface RegisterUiState {
    object RegisterSuccess : RegisterUiState
    object InvalidEmail : RegisterUiState
    object InvalidPassword : RegisterUiState
    object InvalidAge : RegisterUiState
    object UserFound: RegisterUiState
}