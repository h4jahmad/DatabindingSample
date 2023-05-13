package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.common.util.StringUtil
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : LoginUseCase {
    override operator fun invoke(email: String, password: String): LoginResult {
        if (StringUtil.isEmailInvalid(email))
            return LoginResult.InvalidEmail
        if (StringUtil.isPasswordInvalid(password))
            return LoginResult.InvalidPassword

        return when(repository.isUserValid(email, password)){
            is ActionResult.Success -> {
                LoginResult.LoginSuccess
            }
            is ActionResult.Error -> {
                LoginResult.WrongCreds
            }
        }

    }

}