package com.example.databindingsample.auth.usecase

interface LoginUseCase {
    operator fun invoke(email: String, password: String): LoginResult
}