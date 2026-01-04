package com.example.therapet.app.auth.register

object RegisterValidation {
    //Validationg passwords match
    fun passwordsMatch(
        password: String,
        confirmPassword: String
    ): Boolean {
        return confirmPassword.isNotEmpty() &&
                password == confirmPassword
    }

    //Validating the user can register
    fun canRegister(
        userId: String,
        firstName: String,
        surname: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return userId.isNotBlank() &&
                firstName.isNotBlank() &&
                surname.isNotBlank() &&
                PasswordValidator.isPasswordValid(password) &&
                passwordsMatch(password, confirmPassword)
    }
}