package com.example.therapet.ui

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
 *  5. Valid / invalid user IDs
 */

class RegisterUIValidationTest {

    // 1. All fields are valid
    @Test
    fun all_fields_are_valid(){
        val result = RegisterValidation.canRegister(
            userId = "userid123456",
            firstName = "Bob",
            surname = "Bobbington",
            password = "Abcdef1!",
            confirmPassword = "Abcdef1!"
        )
        assertTrue(result)
    }

    // 2. Empty user ID
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

    // 3. Passwords do not match
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

    // 4. Invalid password
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

    //5. Valid / invalid User IDs
    @Test
    fun userIdIsValid_12_to_16_characters_only(){
        assertTrue(RegisterValidation.userIdIsValid("123456789012"))
        assertTrue(RegisterValidation.userIdIsValid("1234567890123456"))
        assertFalse(RegisterValidation.userIdIsValid("12345678901"))
        assertFalse(RegisterValidation.userIdIsValid("1234567890123"))
        assertFalse(RegisterValidation.userIdIsValid("123456789012345"))
        assertFalse(RegisterValidation.userIdIsValid("12345678901234567"))
    }
}