package com.example.databindingsample.auth.usecase

import com.example.databindingsample.MockedUserRepository
import com.example.databindingsample.MockedUserRepository.Companion.users
import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.auth.ui.LoginUiState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginUseCaseTest {

    private val userRepository: UserRepository = MockedUserRepository()
    private val useCase: LoginUseCase = LoginUseCaseImpl(userRepository)

    @Test
    fun `invalid passwords should fail`() {
         assertThat(useCase.login(email = validUser1.email, password ="")).isInstanceOf(LoginUiState.InvalidPassword::class.java)
         assertThat(useCase.login(email = validUser1.email, password ="12345")).isInstanceOf(LoginUiState.InvalidPassword::class.java)
         assertThat(useCase.login(email = validUser1.email, password ="1234525323512")).isInstanceOf(LoginUiState.InvalidPassword::class.java)
         assertThat(useCase.login(email = validUser1.email, password ="464678cfsdcf71sd6cfsdafdc")).isInstanceOf(LoginUiState.InvalidPassword::class.java)
    }

    @Test
    fun `invalid emails should fail`() {
        assertThat(useCase.login(email = "plainaddress", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "#@%^%#$@#$@#.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "@example.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "Joe Smith <email@example.com>", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email.example.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email@example@example.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "あいうえお@example.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email@example.com (Joe Smith)", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email@example", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email@-example.com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "email@example..com", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
        assertThat(useCase.login(email = "", password = validUser1.password)).isInstanceOf(LoginUiState.InvalidEmail::class.java)
    }

    @Test
    fun `wrong creds should fail`() {
        assertThat(useCase.login("wrong@wrongdomain.com", "wrongpasswr")).isInstanceOf(
            LoginUiState.WrongCreds::class.java
        )
    }

    @Test
    fun `valid creds should succeed`() {
        assertThat(useCase.login(validUser1.email, validUser1.password)).isInstanceOf(
            LoginUiState.SuccessLogin::class.java
        )
    }

    private val validUser1 = users[0]

}










