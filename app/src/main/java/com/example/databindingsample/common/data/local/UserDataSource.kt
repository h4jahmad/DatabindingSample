package com.example.databindingsample.common.data.local

import com.example.databindingsample.entities.User

interface UserDataSource {
    fun getUserByEmail(email: String): User?
    fun authenticateUser(email: String, password: String): Boolean
    fun registerNewUser(user: User): Boolean
}