package com.example.therapet.helpers

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.util.crypto.PasswordHasher
import com.example.therapet.app.data.util.crypto.PasswordHasher.toHex

fun createTestUser(
    userid: String,
    firstname: String,
    surname: String,
    password: String,
    role: UserRole
): UserEntity {
    val salt = PasswordHasher.generateSalt()
    val hash = PasswordHasher.hash(password, salt)

    return UserEntity(
        userid = userid,
        firstname = firstname,
        surname = surname,
        passwordHash = hash.toHex(),
        salt = salt.toHex(),
        role = role
    )
}