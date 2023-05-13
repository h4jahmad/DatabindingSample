package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.auth.ui.LoginUiState
import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.common.util.StringUtil
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : LoginUseCase {
    override operator fun invoke(email: String, password: String): LoginUiState {
        if (StringUtil.isEmailInvalid(email))
            return LoginUiState.InvalidEmail
        if (StringUtil.isPasswordInvalid(password))
            return LoginUiState.InvalidPassword

        return when(repository.isUserValid(email, password)){
            is ActionResult.Success -> {
                LoginUiState.LoginSuccess
            }
            is ActionResult.Error -> {
                LoginUiState.WrongCreds
            }
        }

    }

}