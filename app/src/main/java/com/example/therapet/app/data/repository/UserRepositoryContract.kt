package com.example.therapet.app.data.repository

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 *
 */

interface UserRepositoryContract {
    suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    )

    suspend fun login(userid: String, password: String): UserEntity?

    suspend fun userExists(userid: String): Boolean

    suspend fun getUserRole(
        userid: String,
        password: String
    ): UserRole?
}