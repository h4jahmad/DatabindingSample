package com.example.databindingsample.common.util

import java.util.regex.Pattern

object StringUtil {
    private val EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    )

    fun isEmailInvalid(email: String): Boolean =
        email.isBlank() || !EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordInvalid(password: String): Boolean =
        password.isBlank() || !Pattern.matches("^.{6,12}$", password)

}