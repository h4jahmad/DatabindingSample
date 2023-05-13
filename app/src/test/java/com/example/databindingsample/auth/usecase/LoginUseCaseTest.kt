package com.example.databindingsample.auth.usecase

import com.example.databindingsample.MockedUserRepository
import com.example.databindingsample.MockedUserRepository.Companion.users
import com.example.databindingsample.auth.repository.UserRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginUseCaseTest {

    private val userRepository: UserRepository = MockedUserRepository()
    private val useCase: LoginUseCase = LoginUseCaseImpl(userRepository)

    @Test
    fun `invalid passwords should fail`() {
         assertThat(useCase(email = validUser1.email, password ="")).isInstanceOf(LoginResult.InvalidPassword::class.java)
         assertThat(useCase(email = validUser1.email, password ="12345")).isInstanceOf(LoginResult.InvalidPassword::class.java)
         assertThat(useCase(email = validUser1.email, password ="1234525323512")).isInstanceOf(
             LoginResult.InvalidPassword::class.java)
         assertThat(useCase(email = validUser1.email, password ="464678cfsdcf71sd6cfsdafdc")).isInstanceOf(
             LoginResult.InvalidPassword::class.java)
    }

    @Test
    fun `invalid emails should fail`() {
        assertThat(useCase(email = "plainaddress", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "#@%^%#$@#$@#.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "@example.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "Joe Smith <email@example.com>", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email.example.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email@example@example.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "あいうえお@example.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email@example.com (Joe Smith)", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email@example", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email@-example.com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "email@example..com", password = validUser1.password)).isInstanceOf(
            LoginResult.InvalidEmail::class.java)
        assertThat(useCase(email = "", password = validUser1.password)).isInstanceOf(LoginResult.InvalidEmail::class.java)
    }

    @Test
    fun `wrong creds should fail`() {
        assertThat(useCase("wrong@wrongdomain.com", "wrongpasswr")).isInstanceOf(
            LoginResult.WrongCreds::class.java
        )
    }

    @Test
    fun `valid creds should succeed`() {
        assertThat(useCase(validUser1.email, validUser1.password)).isInstanceOf(
            LoginResult.LoginSuccess::class.java
        )
    }

    private val validUser1 = users[0]

}










