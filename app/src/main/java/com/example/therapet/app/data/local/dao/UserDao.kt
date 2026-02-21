package com.example.therapet.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * User data access object
 */

@Dao
interface UserDao {

    /**
     * Inserts a new user record in the database
     *
     * @param user The user to be stored
     */
    @Insert
    suspend fun insertUser(user: UserEntity)

    /**
     * Checks whether a user with the given ID is in the database
     *
     * @param userid The unique ID of the user
     * @return True if the user exists, false otherwise
     */
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE userid = :userid)")
    suspend fun userExists(userid: String): Boolean

    /**
     * Deletes a user from the database by the unique ID
     *
     * @param userid The unique ID of the user to delete
     * @return The number of rows affected
     */
    @Query("DELETE FROM users WHERE userid = :userid")
    suspend fun deleteUserById(userid: String): Int

    /**
     * Retrieves a user from the database by the unique ID
     *
     * @param userid The unique ID of the user
     * @return The matching entity or null if not found
     */
    @Query("SELECT * FROM users WHERE userid = :userid LIMIT 1")
    suspend fun getUserById(userid: String): UserEntity?

    /**
     *Updates a user's password creds
     *
     * @param userid The unique ID of the user
     * @param newPasswordHash generated password hash
     * @param  newSalt the new generated salt used for hashing
     */
    @Query("""
    UPDATE users 
    SET passwordHash = :newPasswordHash,
        salt = :newSalt
    WHERE userid = :userid
""")
    suspend fun updatePassword(
        userid: String,
        newPasswordHash: String,
        newSalt: String
    )

    /**
     * Retrieves all users with a specific role
     *
     * @param role   the users role to filter by
     * @return A list of user objects matching the specific role
     */
    @Query("""
    SELECT * FROM users
    WHERE role = :role
""")
    suspend fun getUsersByRole(role: UserRole): List<UserEntity>

    /**
     * Updates the last login timestamp for a user
     *
     * @param userid The unique ID of the user
     * @param timestamp the login time in milliseconds
     */
    @Query("""
    UPDATE users
    SET lastLoginMillis = :timestamp
    WHERE userid = :userid
""")
    suspend fun updateLastLogin(
        userid: String,
        timestamp: Long
    )
}