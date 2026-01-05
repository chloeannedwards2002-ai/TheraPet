package com.example.therapet.app.auth.register

object RegisterValidation {
    //validating user ID length
    fun userIdIsValid(userId: String): Boolean =
        userId.length >= 16

    //Validating passwords match
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
        return userIdIsValid(userId) &&
                firstName.isNotBlank() &&
                surname.isNotBlank() &&
                PasswordValidator.isPasswordValid(password) &&
                passwordsMatch(password, confirmPassword)
    }
}