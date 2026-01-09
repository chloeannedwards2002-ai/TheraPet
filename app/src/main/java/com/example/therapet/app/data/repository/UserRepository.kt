package com.example.therapet.app.data.repository

import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.entity.UserEntity

class UserRepository(
    private val userDao: UserDao
    ): UserRepositoryContract
{
    //Register
    override suspend fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ){
        userDao.insertUser(
            UserEntity(
                userid = userid,
                firstname = firstname,
                surname = surname,
                password = password
            )
        )
    }

    //Login
    override suspend fun login(
        userid: String,
        password: String
    ): Boolean {
        return userDao.login(userid, password) != null
    }

    // check user exists
    override suspend fun userExists(userid: String): Boolean {
        return userDao.userExists(userid)
    }
}