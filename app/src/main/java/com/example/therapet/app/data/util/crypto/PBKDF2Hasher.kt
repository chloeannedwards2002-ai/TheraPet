package com.example.therapet.app.data.util.crypto

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Password hasher
 *
 * Created using https://blog.codersee.com/kotlin-pbkdf2-secure-password-hashing/
 *
 * PBKDF2 (password based key derivation function 2) - a key derivation function designed for secure password hashing
 *
 * It uses:
 * A salt,
 * A pseudo-random function,
 * A high number of iterations to slow down hashing
 */

object PasswordHasher {

    private const val ALGORITHM = "PBKDF2WithHmacSHA512"
    private const val ITERATIONS = 120_000
    private const val KEY_LENGTH = 256

    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }

    fun hash(password: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        return factory.generateSecret(spec).encoded
    }

    fun verify(password: String, salt: ByteArray, expectedHash: ByteArray): Boolean {
        val hash = hash(password, salt)
        return hash.contentEquals(expectedHash)
    }

    fun ByteArray.toHex(): String =
        joinToString("") { "%02x".format(it) }

    fun hexToBytes(hex: String): ByteArray =
        hex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
}