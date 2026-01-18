package com.example.therapet.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.therapet.app.data.entity.UserEntity

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * Data access object
 */

@Dao

interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    //Login query
    @Query("""
        SELECT * FROM users
        WHERE userid = :userid
        AND password = :password
    """)
    suspend fun login(
        userid: String,
        password: String
    ): UserEntity?

    // Check user exists query
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE userid = :userid)")
    suspend fun userExists(userid: String): Boolean

    @Query("DELETE FROM users WHERE userid = :userid")
    suspend fun deleteUserById(userid: String): Int

    //get user by id
    @Query("SELECT * FROM users WHERE userid = :userid LIMIT 1")
    suspend fun getUserById(userid: String): UserEntity?
}