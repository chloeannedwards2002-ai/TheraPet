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

    @Query("""
        SELECT * FROM users
        WHERE userid = :userid
        AND password = :password
    """)

    suspend fun login(
        userid: String,
        password: String
    ): UserEntity?

}