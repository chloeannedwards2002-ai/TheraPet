package com.example.therapet.app.auth.register

/**
 * @Author: Chloe Edwards
 * @Date: 05/01/2026
 *
 * This object validates the registration as a whole (UI), including password and User ID
 */

object RegisterValidation {
    //validating user ID length
    fun userIdIsValid(userId: String): Boolean =
        (userId.length == 12 || userId.length == 16) && userId.none { it.isWhitespace() } // 12 or more but no more than 16 characters and NO whitespaces

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