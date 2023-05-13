package com.example.databindingsample.auth.usecase

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.common.util.StringUtil
import com.example.databindingsample.entities.User
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : RegisterUseCase {

    override fun invoke(newUser: User): RegisterResult {
        if (StringUtil.isEmailInvalid(newUser.email))
            return RegisterResult.InvalidEmail
        if (StringUtil.isPasswordInvalid(newUser.password))
            return RegisterResult.InvalidPassword
        if (isAgeInvalid(newUser.age))
            return RegisterResult.InvalidAge

        return when (repository.registerNewUser(newUser)) {
            is ActionResult.Success -> RegisterResult.RegisterSuccess
            is ActionResult.Error -> RegisterResult.UserFound
        }
    }

    private fun isAgeInvalid(age: Int): Boolean = age < 18 || age > 99

}









