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
    suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    )

    suspend fun login(userid: String, password: String): UserEntity?

    suspend fun userExists(userid: String): Boolean

    suspend fun deleteUser(userid: String)

    // get user without needing password
    suspend fun getUserById(userid: String): UserEntity?

    suspend fun updatePassword(
        userid: String,
        newPassword: String
    ): Boolean

    suspend fun getTherapists(): List<UserEntity>

    //Used for user detail cards
    suspend fun getUserAccountById(userid: String): Pair<AccountUIModel, UserRole>?
}