package com.example.therapet.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.local.dao.UserDao
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
                password = "_Password_123"
            )
        )
        assertTrue(dao.userExists("MNU82910CWLP"))
    }

    @Test
    fun login_returns_correct_user_when_details_match() = runBlocking {
        dao.insertUser(
            UserEntity(
                userid = "MNU82910CWLP",
                firstname = "Bill",
                surname = "Billington",
                password = "_Password_123"
            )
        )

        val user  =dao.login("MNU82910CWLP", "_Password_123")
        assertNotNull(user)
    }
}