package com.example.therapet.app.data.repository

import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract
import com.example.therapet.app.data.util.crypto.PasswordHasher
import com.example.therapet.app.data.util.crypto.PasswordHasher.toHex

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
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        userDao.insertUser(
            UserEntity(
                userid = userid,
                firstname = firstname,
                surname = surname,
                passwordHash = hash.toHex(),
                salt = salt.toHex(),
                role = role
            )
        )
    }

    //Login
    override suspend fun login(
        userid: String,
        password: String
    ): UserEntity? {

        val user = userDao.getUserById(userid) ?: return null

        val saltBytes = PasswordHasher.hexToBytes(user.salt)
        val hashBytes = PasswordHasher.hexToBytes(user.passwordHash)

        return if (PasswordHasher.verify(password, saltBytes, hashBytes)) {
            user
        } else {
            null
        }
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

    override suspend fun getUserById(userid: String): UserEntity? {
        return userDao.getUserById(userid)
    }

    override suspend fun updatePassword(userid: String, newPassword: String): Boolean {
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(newPassword, salt)

        userDao.updatePassword(
            userid = userid,
            newPasswordHash = hash.toHex(),
            newSalt = salt.toHex()
        )
        return true
    }

    override suspend fun getTherapists(): List<UserEntity> {
        return userDao.getUsersByRole(UserRole.THERAPIST)
    }
}