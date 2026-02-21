package com.example.therapet.helpers

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.util.crypto.PasswordHasher
import com.example.therapet.app.data.util.crypto.PasswordHasher.toHex

/**
 * @author: Chloe Edwards
 * @date edited: 21/02/2026
 *
 * CreateTestUser
 *
 * A utility function that generates a UserEntity for testing (automatically hashes the password)
 */

fun createTestUser(
    userid: String,
    firstname: String,
    surname: String,
    password: String,
    role: UserRole
): UserEntity {
    // Generates a salt for password hashing
    val salt = PasswordHasher.generateSalt()
    // Hashes the plaintext password using the salt
    val hash = PasswordHasher.hash(password, salt)

    // Return the test user entity with the hashed password and salt encoded hex
    return UserEntity(
        userid = userid,
        firstname = firstname,
        surname = surname,
        passwordHash = hash.toHex(),
        salt = salt.toHex(),
        role = role
    )
}