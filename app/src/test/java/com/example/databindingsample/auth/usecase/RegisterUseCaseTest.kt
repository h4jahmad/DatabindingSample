package com.example.databindingsample.auth.usecase

import com.example.databindingsample.MockedUserRepository
import com.example.databindingsample.MockedUserRepository.Companion.users
import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.entities.User
import com.google.common.truth.Truth.*
import org.junit.Test

class RegisterUseCaseTest {

    private val userRepository: UserRepository = MockedUserRepository()
    private val useCase: RegisterUseCase = RegisterUseCaseImpl(userRepository)

    @Test
    fun `invalid passwords should fail`() {
        assertThat(useCase(User(email = validUser.email, password = "", age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidPassword::class.java)
        assertThat(useCase(User(email = validUser.email, password = "12345", age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidPassword::class.java)
        assertThat(useCase(User(email = validUser.email, password = "1234525323512", age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidPassword::class.java)
        assertThat(useCase(User(email = validUser.email, password = "464678cfsdcf71sd6cfsdafdc", age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidPassword::class.java)
    }

    @Test
    fun `invalid emails should fail`() {
        assertThat(useCase(User(email = "plainaddress", password = validUser.password, age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "#@%^%#$@#$@#.com", password = validUser.password, age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "@example.com", password = validUser.password, age= validUser.age)))
            .isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(
                email = "Joe Smith <email@example.com>",
                password = validUser.password,
                age= validUser.age
            ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "email.example.com", password = validUser.password,
            age= validUser.age
        )))
            .isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(
                email = "email@example@example.com",
                password = validUser.password,
                age= validUser.age
            ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "あいうえお@example.com", password = validUser.password,
            age= validUser.age
        ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(
                email = "email@example.com (Joe Smith)",
                password = validUser.password,
                age= validUser.age
            ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "email@example", password = validUser.password,age= validUser.age
        ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "email@-example.com", password = validUser.password,age= validUser.age
        ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "email@example..com", password = validUser.password,age= validUser.age
        ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
        assertThat(useCase(User(email = "", password = validUser.password,age= validUser.age
        ))).isInstanceOf(RegisterResult.InvalidEmail::class.java)
    }

    @Test
    fun `invalid age should fail`(){
        assertThat(useCase(User(email = validUser.email, password = validUser.password,age= 10
        ))).isInstanceOf(RegisterResult.InvalidAge::class.java)
        assertThat(useCase(User(email = validUser.email, password = validUser.password,age= -1
        ))).isInstanceOf(RegisterResult.InvalidAge::class.java)
        assertThat(useCase(User(email = validUser.email, password = validUser.password,age= 100
        ))).isInstanceOf(RegisterResult.InvalidAge::class.java)
    }

    @Test
    fun `email found, should fail`(){
        assertThat(useCase(User(email = users[0].email, password = validUser.password, age= validUser.age
        ))).isInstanceOf(RegisterResult.UserFound::class.java)
    }

    @Test
    fun `right info, should succeed`(){
        assertThat(useCase(User(email = validUser.email, password = validUser.password, age= validUser.age
        ))).isInstanceOf(RegisterResult.RegisterSuccess::class.java)
    }

    private val validUser = User(
        email = "newuser@domain.com",
        password = "qwe123_+",
        age = 23
    )
}