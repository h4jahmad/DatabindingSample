package com.example.databindingsample.auth.repository

import com.example.databindingsample.common.ActionResult
import com.example.databindingsample.entities.User

interface UserRepository {
    fun isUserValid(email: String, password: String): ActionResult<Unit>
    fun registerNewUser(newUser: User): ActionResult<Unit>
}