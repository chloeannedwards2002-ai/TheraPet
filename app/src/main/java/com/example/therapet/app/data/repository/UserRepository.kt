package com.example.therapet.app.data.repository

import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract

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
        val role = determineUserRole(userid)

        userDao.insertUser(
            UserEntity(
                userid = userid,
                firstname = firstname,
                surname = surname,
                password = password,
                role = role
            )
        )
    }

    //Login
    override suspend fun login(
        userid: String,
        password: String
    ): UserEntity? {
        return userDao.login(userid, password)
    }

    // check user exists
    override suspend fun userExists(userid: String): Boolean {
        return userDao.userExists(userid)
    }

    //Delete user
    override suspend fun deleteUser(userid: String) {
        userDao.deleteUserById(userid)
    }

    // determining the users role - invalid user ID is already caught in UI but this is ane xtra defense
    private fun determineUserRole(userid: String): UserRole {
        return when(userid.length){
            12 -> UserRole.PATIENT
            16 -> UserRole.THERAPIST
            else -> throw IllegalArgumentException("Invalid user ID")
        }
    }

    //retrieves the user role
    override suspend fun getUserRole(
        userid: String,
        password: String
    ): UserRole? {
        return userDao.login(userid, password)?.role
    }


    override suspend fun getUserById(userid: String): UserEntity? {
        return userDao.getUserById(userid)
    }

    override suspend fun updatePassword(
        userid: String,
        newPassword: String
    ): Boolean {
        userDao.updatePassword(
            userid = userid,
            newPassword = newPassword
        )
        return true
    }

    override suspend fun getTherapists(): List<UserEntity> {
        return userDao.getUsersByRole(UserRole.THERAPIST)
    }
}