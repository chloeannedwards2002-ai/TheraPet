package com.example.therapet.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.UserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * User repository tests
 * 1. Registration
 * 2. Logging in with valid details
 * 3. Logging in with invalid details
 */

class UserRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepository

    //Setting up the in-memory room database
    @Before
    fun setup(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries().build()

        userDao = db.userDao()
        repository = UserRepository(userDao)
    }

    @After
    fun shutdown(){
        db.close()
    }

    //1. Registration - no invalid test needed because invalid details are caught by the UI and register button is disabled until details ARE valid
    @Test
    fun registering_inserts_user_into_db() = runBlocking {
        repository.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")

        val exists = repository.userExists("X328DFSJ108Z")
        Assert.assertTrue(exists)
    }

    //2. Logging in with valid details TODO: Implement login state functionality
    @Test
    fun login_with_valid_details() = runBlocking {
        repository.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")

        val result = repository.login("X328DFSJ108Z", "Password_123")
    }

    // 3. Logging in with invalid details TODO: Implement login state functionality
    @Test
    fun login_with_invalid_details() = runBlocking {
        val result = repository.login("83248ndsfnskjjesnf838", "invalid")
        Assert.assertNull(result)
    }

    // 4.
    @Test
    fun registering_with_12_char_id_creates_patient_record() = runBlocking {
        repository.register("123123123123", "test", "test", "Password_123")

        //Not ideal way of testing but works for now
        val user = repository.login("123123123123", "Password_123") // returns UserEntity?
        assertNotNull(user)

        assertEquals(UserRole.PATIENT, user?.role)
    }
}