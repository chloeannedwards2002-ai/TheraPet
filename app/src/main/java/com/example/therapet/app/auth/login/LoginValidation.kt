package com.example.therapet.app.auth.login

/**
 * @Author: Chloe Edwards
 * @Date: 09/01/2026
 *
 * This object validates the login as a whole (UI),password and User ID
 */

object LoginValidation {
    fun canLogin(
        userId: String,
        password: String
    ): Boolean {
        return userId.isNotBlank() && password.isNotBlank()
    }
}