package com.example.databindingsample.auth.ui

data class RegisterUiState(
    val errorMessage: Int? = null,
    val shouldShowSnackbar: Boolean = false,
    val isUserLoggedIn: Boolean = false,
)