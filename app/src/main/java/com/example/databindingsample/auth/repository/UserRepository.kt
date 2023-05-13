package com.example.databindingsample.auth.repository

import com.example.databindingsample.common.ActionResult

interface UserRepository {
    fun isUserValid(email: String, password: String): ActionResult<Unit>
}