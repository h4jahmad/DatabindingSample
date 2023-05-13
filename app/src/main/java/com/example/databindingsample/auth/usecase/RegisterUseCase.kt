package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.ui.RegisterUiState
import com.example.databindingsample.entities.User

interface RegisterUseCase {
    operator fun invoke(newUser: User): RegisterUiState
}