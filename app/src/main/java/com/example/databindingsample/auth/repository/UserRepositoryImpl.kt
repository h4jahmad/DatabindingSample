package com.example.databindingsample.auth.repository

import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.common.data.local.UserDataSource
import com.example.databindingsample.entities.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource,
) : UserRepository {

    override fun isUserValid(email: String, password: String): ActionResult<Unit> {
        if (dataSource.authenticateUser(email, password)) {
            return ActionResult.Success(Unit)
        }
        return ActionResult.Error(Exception("User not found."))
    }

    override fun registerNewUser(newUser: User): ActionResult<Unit> {
        return if (!dataSource.registerNewUser(newUser))
            ActionResult.Error(Exception("There's already an account with a same email."))
        else
            ActionResult.Success(Unit)
    }
}