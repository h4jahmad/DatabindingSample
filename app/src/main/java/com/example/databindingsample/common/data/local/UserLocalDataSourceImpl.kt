package com.example.databindingsample.common.data.local

import com.example.databindingsample.entities.User

class UserLocalDataSourceImpl : UserDataSource {

    private val users = mutableListOf(
        User(
            email = "shon@360mea.com",
            password = "thisisyourpasswordshon",
            age = 25
        ),
        User(
            email = "tiago.alves@360mea.com",
            password = "thisisyourpasswordtiago",
            age = 25
        ),
    )

    override fun getUser(email: String): User? = users.find { it.email == email }
    override fun getUser(user: User): User? = users.find { it == user }

    override fun authenticateUser(email: String, password: String): Boolean =
        users.find { it.email == email && it.password == password } != null

    override fun register(user: User): Boolean {
        if (getUser(user) == null) {
            users.add(user)
            return true
        }
        return false
    }


}