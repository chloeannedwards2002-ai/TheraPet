package com.example.therapet

import com.example.therapet.app.auth.register.PasswordValidator
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

class PasswordUIValidationTest {

    // 1. Testing a valid password is valid
    @Test
    fun password_is_valid()
    {
        // valid password is 8 char long, 1 special character, 1 capital letter and 1 number
        val password = "Ab1_cdefg!"
        assertTrue(PasswordValidator.isPasswordValid(password))
    }

    // 2. Password is too short
    @Test
    fun password_too_short_is_invalid(){
        // invalid password - not 8 characters long
        val password = "Ab1!"
        assertFalse(PasswordValidator.isPasswordValid(password))
    }

    // 3. Password without number
    @Test
    fun password_without_number_is_invalid(){
        // invalid password - missing a number
        val password = "Abcdefg!"
        assertFalse(PasswordValidator.isPasswordValid(password))
    }

    // 4. Password without special character
    @Test
    fun password_without_special_character_is_invalid(){
        // invalid password - missing a number
        val password = "Abcdefg1"
        assertFalse(PasswordValidator.isPasswordValid(password))
    }

    // 5. Password without uppercase
    @Test
    fun password_without_uppercase_is_invalid(){
        // invalid password - missing a number
        val password = "abcdef1!"
        assertFalse(PasswordValidator.isPasswordValid(password))
    }
}