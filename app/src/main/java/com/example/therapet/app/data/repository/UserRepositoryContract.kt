package com.example.therapet.app.data.repository

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

    suspend fun login(userid: String, password: String): Boolean

    suspend fun userExists(userid: String): Boolean
}