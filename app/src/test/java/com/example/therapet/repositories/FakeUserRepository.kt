package com.example.therapet.repositories

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * A fake user repository used in unit tests
 */

class FakeUserRepository : UserRepositoryContract {
    private val users = mutableMapOf<String, UserEntity>()

    override suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ) {
        val role = determineUserRole(userid)
        users[userid] = UserEntity(
            userid = userid,
            firstname = firstname,
            surname = surname,
            password = password,
            role = role
        )
    }

    override suspend fun login(userid: String, password: String): UserEntity? {
        val user = users[userid]
        return if (user != null && user.password == password) user else null
    }

    override suspend fun userExists(userid: String): Boolean {
        return users.containsKey(userid)
    }

    override suspend fun getUserRole(userid: String, password: String): UserRole? {
        return login(userid, password)?.role
    }

    override suspend fun deleteUser(userid: String){
        users.remove(userid)
    }

    private fun determineUserRole(userid: String): UserRole {
        return when (userid.length) {
            12 -> UserRole.PATIENT
            16 -> UserRole.THERAPIST
            else -> throw IllegalArgumentException("Invalid user ID")
        }
    }
}