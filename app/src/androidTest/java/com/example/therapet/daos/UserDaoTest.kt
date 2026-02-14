package com.example.therapet.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.util.crypto.PasswordHasher
import com.example.therapet.app.data.util.crypto.PasswordHasher.toHex
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * User Dao Tests
 * 1. Insert user details then check that user exists in the database
 * 2. Login and ensure the details match those in the database
 */

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao
    val password = "Password_123"
    val salt = PasswordHasher.generateSalt()
    val hash = PasswordHasher.hash(password, salt)


    //Setting up the in-memory room database
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun shutdown(){
        database.close()
    }

    @Test
    fun insert_details_check_user_exists() = runBlocking {
        dao.insertUser(
            UserEntity(
                userid = "MNU82910CWLP",
                firstname = "Bill",
                surname = "Billington",
                passwordHash = hash.toHex(),
                salt = salt.toHex(),
                role = UserRole.PATIENT
            )
        )
        assertTrue(dao.userExists("MNU82910CWLP"))
    }

    @Test
    fun login_returns_correct_user_when_details_match() = runBlocking {
        val password = "_Password_123"
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)

        dao.insertUser(
            UserEntity(
                userid = "MNU82910CWLP",
                firstname = "Bill",
                surname = "Billington",
                passwordHash = hash.toHex(),
                salt = salt.toHex(),
                role = UserRole.PATIENT
            )
        )

        val user = dao.getUserById("MNU82910CWLP")
        assertNotNull(user)

        val isValid = PasswordHasher.verify(
            password,
            PasswordHasher.hexToBytes(user!!.salt),
            PasswordHasher.hexToBytes(user.passwordHash)
        )
        assertTrue(isValid)
    }
}