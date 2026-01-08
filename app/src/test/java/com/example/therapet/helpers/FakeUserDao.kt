package com.example.therapet.helpers

import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.dao.UserDao

/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * This file fakes a user DAO  so I can test logic without Room
 */

class FakeUserDao: UserDao {

    private val users = mutableListOf<UserEntity> ()

    // registration
    override suspend fun insertUser(user: UserEntity) {
        users.add(user)
    }

    // logging in
    override suspend fun login(userid: String, password: String): UserEntity? {
        return users.find { it.userid == userid && it.password == password }
    }

    // does user exist
    override suspend fun userExists(userid: String): Boolean {
        return users.any { it.userid == userid }
    }
}