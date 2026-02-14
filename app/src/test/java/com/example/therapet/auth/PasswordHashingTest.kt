package com.example.therapet.auth

import com.example.therapet.app.data.util.crypto.PasswordHasher
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Password hashing tests (PBKDF2)
 *
 * 1. Testing matching password and salt generates a matching hash
 * 2. Testing a matching password but different salt generates a unique hash
 * 3. Correct password verification
 * 4. Incorrect password verification
 */

class PasswordHashingTest{

    //1. Testing matching password and salt generates a matching hash
    /**
     * When logging in, the password is hashed with a salt and compares it to the stored hash,
     *  if hashing doesn't match the user cannot login even with the right password
     */
    @Test
    fun matchingPasswordAndSaltGenerateMatchingHash() {
        val password = "Secure_123"
        val salt = PasswordHasher.generateSalt()

        val hash1 = PasswordHasher.hash(password, salt)
        val hash2 = PasswordHasher.hash(password, salt)

        assertArrayEquals(hash1, hash2)
    }

    //2. Testing a matching password but different salt generates a unique hash
    /**
     * If two users use the same password, the hashes should be different otherwise hackers can
     * detect duplicate passwords across the accounts
     */
    @Test
    fun matchingPasswordDifferentSaltsGenerateDifferentHash() {
        val password = "Secure_123"

        val salt1 = PasswordHasher.generateSalt()
        val salt2 = PasswordHasher.generateSalt()

        val hash1 = PasswordHasher.hash(password, salt1)
        val hash2 = PasswordHasher.hash(password, salt2)

        assertFalse(hash1.contentEquals(hash2))
    }

    //3. Correct password verification
    /**
     * Confirms hashing, salt and comparison logic
     */
    @Test
    fun verifyReturnsTrueForCorrectPassword() {
        val password = "pasSwOrd_123"
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        val result = PasswordHasher.verify(password, salt, hash)
        assertTrue(result)
    }

    //4. Incorrect password verification
    /**
     * Incorrect password are rejected
     */
    @Test
    fun verifyReturnsFalseForIncorrectPassword() {
        val password = "pasSwOrd_123"
        val wrongPassword = "pasSwOrd_1234"
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        val result = PasswordHasher.verify(wrongPassword, salt, hash)
        assertFalse(result)
    }
}
