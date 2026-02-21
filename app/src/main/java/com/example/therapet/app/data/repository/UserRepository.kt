package com.example.therapet.app.data.repository

import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.model.toAccountUIModel
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract
import com.example.therapet.app.data.util.crypto.PasswordHasher
import com.example.therapet.app.data.util.crypto.PasswordHasher.toHex
import com.example.therapet.app.data.model.AccountUIModel

/**
 * Implementation of UserRepositoryContract
 *
 * Handles user registration, login, passwor dupdates etc
 */

class UserRepository(
    private val userDao: UserDao
    ): UserRepositoryContract
{
    /**
     * Registers a new user
     *
     * User role is based on the ID length
     * Generates a salt
     * Hashes the password
     */
    override suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ){
        val role = determineUserRole(userid)
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        userDao.insertUser(
            UserEntity(
                userid = userid,
                firstname = firstname,
                surname = surname,
                passwordHash = hash.toHex(),
                salt = salt.toHex(),
                role = role
            )
        )
    }

    /**
     * Logs in the user by verifying the password
     * Updates the last login timestamp
     *
     * @return UserEntity if login succeeds, null if creds are invalid
     */
    override suspend fun login(
        userid: String,
        password: String
    ): UserEntity? {

        val user = userDao.getUserById(userid) ?: return null

        val saltBytes = PasswordHasher.hexToBytes(user.salt)
        val hashBytes = PasswordHasher.hexToBytes(user.passwordHash)

        return if (PasswordHasher.verify(password, saltBytes, hashBytes)) {
            userDao.updateLastLogin(userid, System.currentTimeMillis())
            user.copy(lastLoginMillis = System.currentTimeMillis())
        } else {
            null
        }
    }

    /**
     * Checks if the user with given ID exists
     */
    override suspend fun userExists(userid: String): Boolean {
        return userDao.userExists(userid)
    }

    /**
     * Deletes a user from the database
     */
    override suspend fun deleteUser(userid: String) {
        userDao.deleteUserById(userid)
    }

    /**
     * Determines a user's role based on the ID format
     *
     * 12 characters - patients
     * 16 characters - therapists
     */
    private fun determineUserRole(userid: String): UserRole {
        return when(userid.length){
            12 -> UserRole.PATIENT
            16 -> UserRole.THERAPIST
            else -> throw IllegalArgumentException("Invalid user ID")
        }
    }

    /**
     * Retrieves a user by their ID
     */
    override suspend fun getUserById(userid: String): UserEntity? {
        return userDao.getUserById(userid)
    }

    /**
     * Updates a user's password
     *
     * Generates a new salt and hash before saving
     */
    override suspend fun updatePassword(userid: String, newPassword: String): Boolean {
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(newPassword, salt)

        userDao.updatePassword(
            userid = userid,
            newPasswordHash = hash.toHex(),
            newSalt = salt.toHex()
        )
        return true
    }

    /**
     * Retrieves all users with the therapist role
     */
    override suspend fun getTherapists(): List<UserEntity> {
        return userDao.getUsersByRole(UserRole.THERAPIST)
    }

    /**
     * Retrieves account details formatted for UI, along with user's role
     */
    override suspend fun getUserAccountById(userid: String): Pair<AccountUIModel, UserRole>? {
        val user = userDao.getUserById(userid) ?: return null
        return user.toAccountUIModel() to user.role
    }

    /**
     * Updates the last login timestamp for the user
     */
    override suspend fun updateLastLogin(userid: String) {
        userDao.updateLastLogin(
            userid = userid,
            timestamp = System.currentTimeMillis()
        )
    }
}