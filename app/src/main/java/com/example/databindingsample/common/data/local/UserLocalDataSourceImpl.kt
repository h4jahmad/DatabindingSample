package com.example.databindingsample.common.data.local

import com.example.databindingsample.entities.User

class UserLocalDataSourceImpl : UserDataSource {

    private val users = mutableListOf(
        User(
            email = "first@domain.com",
            password = "pswordfirst",
            age = 25
        ),
        User(
            email = "second@domain.com",
            password = "pswordsecond",
            age = 25
        ),
    )

    override fun getUserByEmail(email: String): User? = users.find { it.email == email }

    override fun authenticateUser(email: String, password: String): Boolean =
        users.find { it.email == email && it.password == password } != null

    override fun registerNewUser(user: User): Boolean {
        if (getUserByEmail(user.email) == null) {
            users.add(user)
            return true
        }
        return false
    }

}