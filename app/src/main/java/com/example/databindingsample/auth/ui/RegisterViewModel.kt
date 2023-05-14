package com.example.databindingsample.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingsample.R
import com.example.databindingsample.auth.usecase.RegisterResult
import com.example.databindingsample.auth.usecase.RegisterUseCase
import com.example.databindingsample.common.util.safeValue
import com.example.databindingsample.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val age = MutableLiveData("")

    val emailErrorResId = MutableLiveData(-1)
    val passwordErrorResId = MutableLiveData(-1)
    val ageErrorResId = MutableLiveData(-1)

    fun register() {
        if(age.safeValue.toIntOrNull() == null){
            ageErrorResId.postValue(R.string.invalid_age_error)
            return
        }
        when (useCase(
            User(
                email = email.safeValue,
                password = password.safeValue,
                age = age.safeValue.toInt()
            )
        )) {
            RegisterResult.InvalidEmail -> emailErrorResId.postValue(R.string.invalid_email_error)
            RegisterResult.InvalidPassword -> passwordErrorResId.postValue(R.string.invalid_password_error)
            RegisterResult.InvalidAge -> ageErrorResId.postValue(R.string.invalid_age_error)
            RegisterResult.UserFound -> _uiState.update {
                it.copy(
                    errorMessage = R.string.user_found_error,
                    shouldShowSnackbar = true
                )
            }

            RegisterResult.RegisterSuccess -> _uiState.update { it.copy(isUserLoggedIn = true) }
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










