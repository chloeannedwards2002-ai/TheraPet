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

    @Insert
    suspend fun insertUser(user: UserEntity)

    // Check user exists query
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE userid = :userid)")
    suspend fun userExists(userid: String): Boolean

    @Query("DELETE FROM users WHERE userid = :userid")
    suspend fun deleteUserById(userid: String): Int

    //get user by id
    @Query("SELECT * FROM users WHERE userid = :userid LIMIT 1")
    suspend fun getUserById(userid: String): UserEntity?

    //update password
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

    // get user by role
    @Query("""
    SELECT * FROM users
    WHERE role = :role
""")
    suspend fun getUsersByRole(role: UserRole): List<UserEntity>

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