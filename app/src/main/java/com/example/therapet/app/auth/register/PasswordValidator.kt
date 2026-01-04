package com.example.therapet.app.auth.register

/**
 * @Author: Chloe Edwards
 * @Date: 03/01/2026
 *
 * This file is a password validator object that uses PasswordRequirement.kt to validate
 * the users password
 */

object PasswordValidator {

    fun validate(password: String): List<PasswordRequirement>
    {
        return listOf(
            PasswordRequirement(
                label = "Password must include at least 8 characters",
                reqIsMet = password.length >= 8
            ),
            PasswordRequirement(
                label = "Password must include at least 1 capital letter",
                reqIsMet = password.any { it.isUpperCase() }
            ),
            PasswordRequirement(
                label = "Password must include at least 1 number",
                reqIsMet = password.any { it.isDigit() }
            ),
            PasswordRequirement(
                label = "Password must include at least 1 special character",
                reqIsMet = password.any { !it.isLetterOrDigit() }
            )
        )
    }

    fun isPasswordValid(password: String): Boolean =
        validate(password).all { it.reqIsMet }
}