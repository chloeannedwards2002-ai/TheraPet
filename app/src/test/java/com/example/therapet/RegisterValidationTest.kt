package com.example.therapet

import com.example.therapet.app.auth.register.RegisterValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @Author: Chloe Edwards
 * @Date: 04/01/2026
 *
 * These tests are for password validation (register screen only for now)
 *
 * Password validation tests:
 *  1. Testing a valid password is valid
 *  2. Password is too short
 *  3. Password without number
 *  4. Password without special character
 *  5. Password without uppercase
 */

class RegisterValidationTest {
    @Test
    fun all_fields_are_valid(){
        val result = RegisterValidation.canRegister(
            userId = "userid123",
            firstName = "Bob",
            surname = "Bobbington",
            password = "Abcdef1!",
            confirmPassword = "Abcdef1!"
        )
        assertTrue(result)
    }

    @Test
    fun empty_user_id() {
        val result = RegisterValidation.canRegister(
            userId = "",
            firstName = "Bob",
            surname = "Bobbington",
            password = "Abcdef1!",
            confirmPassword = "Abcdef1!"
        )
        assertFalse(result)
    }

    @Test
    fun passwords_do_not_match(){
        val result = RegisterValidation.canRegister(
            userId = "",
            firstName = "Bob",
            surname = "Bobbington",
            password = "Abcdef1!",
            confirmPassword = "Abcdef12!"
        )
        assertFalse(result)
    }

    @Test
    fun invalid_password(){
        val result = RegisterValidation.canRegister(
            userId = "userid123",
            firstName = "Bob",
            surname = "Bobbington",
            password = "abc",
            confirmPassword = "abc"
        )

        assertFalse(result)
    }


}