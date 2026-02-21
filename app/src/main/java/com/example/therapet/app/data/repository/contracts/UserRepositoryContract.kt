package com.example.therapet.app.data.repository.contracts

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * User repo contract (abstraction)
 */

interface UserRepositoryContract {
    /**
     * Registers a new account
     */
    suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    )

    /**
     * Attempts to log a user in
     */
    suspend fun login(userid: String, password: String): UserEntity?

    /**
     * Checks whether a user ID already exists
     */
    suspend fun userExists(userid: String): Boolean

    /**
     * Deletes a user account by ID
     */
    suspend fun deleteUser(userid: String)

    /**
     * Retrieves a user by ID without requiring a password
     */
    suspend fun getUserById(userid: String): UserEntity?

    /**
     * Updates a user's password
     */
    suspend fun updatePassword(
        userid: String,
        newPassword: String
    ): Boolean

    /**
     * Returns all the therapists
     */
    suspend fun getTherapists(): List<UserEntity>

    /**
     * Retrieves account details formatted for UI display
     */
    suspend fun getUserAccountById(userid: String): Pair<AccountUIModel, UserRole>?

    /**
     * Update the user's last login timestamp
     */
    suspend fun updateLastLogin(userid: String)
}