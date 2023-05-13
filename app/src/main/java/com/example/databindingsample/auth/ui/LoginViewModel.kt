package com.example.databindingsample.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingsample.R
import com.example.databindingsample.auth.usecase.LoginResult
import com.example.databindingsample.auth.usecase.LoginUseCase
import com.example.databindingsample.common.util.safeValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val emailErrorResId = MutableLiveData(-1)
    val passwordErrorResId = MutableLiveData(-1)

    fun login() {
        when (loginUseCase(email.safeValue, password.safeValue)) {
            LoginResult.InvalidEmail -> emailErrorResId.postValue(R.string.invalid_email_error)
            LoginResult.InvalidPassword -> passwordErrorResId.postValue(R.string.invalid_password_error)
            LoginResult.WrongCreds -> _uiState.update {
                it.copy(
                    errorMessage = R.string.wrong_creds_error,
                    shouldShowSnackbar = true
                )
            }
            LoginResult.LoginSuccess -> _uiState.update { it.copy(isUserLoggedIn = true) }
        }
    }

    fun setErrorDismissed() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                shouldShowSnackbar = false,
            )
        }
    }
}









