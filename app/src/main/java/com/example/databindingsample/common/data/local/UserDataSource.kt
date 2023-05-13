package com.example.databindingsample.common.data.local

import com.example.databindingsample.entities.User

interface UserDataSource {
    fun getUser(email: String): User?
    fun getUser(user: User): User?
    fun authenticateUser(email: String, password: String): Boolean
    fun register(user: User): Boolean
}