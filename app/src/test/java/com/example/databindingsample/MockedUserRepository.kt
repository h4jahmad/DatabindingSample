package com.example.databindingsample

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.entities.User


class MockedUserRepository : UserRepository {

    override fun isUserValid(email: String, password: String): ActionResult<Unit> {
        if (users.find { it.email == email && it.password == password } == null) {
            return ActionResult.Error(Exception("User not found."))
        }
        return ActionResult.Success(Unit)
    }

    companion object {
        val users = mutableListOf(
            User(
                email = "first@domain.com",
                password = "123456778",
                age = 55
            ),
            User(
                email = "second@domain.com",
                password = "12353er456778",
                age = 43
            ),
        )
    }


}