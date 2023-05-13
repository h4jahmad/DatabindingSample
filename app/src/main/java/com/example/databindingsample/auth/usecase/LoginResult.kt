package com.example.databindingsample.auth.usecase

sealed interface LoginResult {
    object LoginSuccess: LoginResult
    object WrongCreds: LoginResult
    object InvalidEmail: LoginResult
    object InvalidPassword: LoginResult
}