package com.example.therapet

import com.example.therapet.app.auth.register.RegisterValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @Author: Chloe Edwards
 * @Date: 04/01/2026
 *
 * These tests are for registration validation
 *
 * Password validation tests:
 *  1. All fields are valid
 *  2. Empty user ID
 *  3. Passwords do not match
 *  4. Invalid password
 *  5.
 */

class RegisterValidationTest {
    @Test
    fun all_fields_are_valid(){
        val result = RegisterValidation.canRegister(
            userId = "userid1234567891",
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

    @Test
    fun user_id_shorter_than_16_characters_is_invalid(){
        val result = RegisterValidation.canRegister(
            userId = "ID",
            firstName = "Bob",
            surname = "Bobbington",
            password = "Abcdef1!",
            confirmPassword = "Abcdef1!"
        )

        assertFalse(result)
    }

}