package com.example.therapet.repositories

import com.example.therapet.app.data.repository.UserRepositoryContract

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * A fake user repository used in unit tests
 */

class FakeUserRepository : UserRepositoryContract {
    private val users = mutableMapOf<String, String>()

    override suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ) {
        users[userid] = password
    }

    override suspend fun login(userid: String, password: String): Boolean {
        return users[userid] == password
    }

    override suspend fun userExists(userid: String): Boolean {
        return users.containsKey(userid)
    }
}