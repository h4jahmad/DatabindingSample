package com.example.databindingsample.auth.usecase

sealed interface RegisterResult {
    object RegisterSuccess : RegisterResult
    object InvalidEmail : RegisterResult
    object InvalidPassword : RegisterResult
    object InvalidAge : RegisterResult
    object UserFound: RegisterResult
}