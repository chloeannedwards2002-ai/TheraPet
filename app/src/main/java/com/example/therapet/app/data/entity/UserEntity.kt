package com.example.therapet.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * The user database table
 */

@Entity(tableName = "users")

data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userid: String,
    val firstname: String,
    val surname: String,
    val password: String,
    val role: UserRole
)
