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

    /**
     * Generates a random salt for password hashing
     *
     * @return 16-byte array containing the salt
     */
    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }

    /**
     * Hashes a password using the given salt and PBKDF2
     *
     * @param password the password to hash
     * @param salt the salt to use for hashing
     * @return the hashed password as a byte array
     */
    fun hash(password: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        return factory.generateSecret(spec).encoded
    }

    /**
     * Verifies a password against a given hash and salt
     *
     * @param password THe password to verify
     * @param salt the sale used when hashing the original password
     * @param expectedHash the expected hash to compare against
     * @return true ifthe password is correct
     */
    fun verify(password: String, salt: ByteArray, expectedHash: ByteArray): Boolean {
        val hash = hash(password, salt)
        return hash.contentEquals(expectedHash)
    }

    /**
     * Converts a byte array to a hex string
     */
    fun ByteArray.toHex(): String =
        joinToString("") { "%02x".format(it) }

    /**
     * Converts a hex string back into a byte array
     */
    fun hexToBytes(hex: String): ByteArray =
        hex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
}