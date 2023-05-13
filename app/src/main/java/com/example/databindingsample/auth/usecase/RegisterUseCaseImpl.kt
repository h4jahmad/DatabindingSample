package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.auth.ui.RegisterUiState
import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.common.util.StringUtil
import com.example.databindingsample.entities.User
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : RegisterUseCase {

    override fun invoke(newUser: User): RegisterUiState {
        if (StringUtil.isEmailInvalid(newUser.email))
            return RegisterUiState.InvalidEmail
        if (StringUtil.isPasswordInvalid(newUser.password))
            return RegisterUiState.InvalidPassword
        if (isAgeInvalid(newUser.age))
            return RegisterUiState.InvalidAge

        return when (repository.registerNewUser(newUser)) {
            is ActionResult.Success -> RegisterUiState.RegisterSuccess
            is ActionResult.Error -> RegisterUiState.UserFound
        }
    }

    private fun isAgeInvalid(age: Int): Boolean = age < 18 || age > 99

}









