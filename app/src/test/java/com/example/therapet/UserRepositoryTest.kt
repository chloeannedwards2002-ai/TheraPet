package com.example.therapet

import com.example.therapet.app.data.repository.UserRepository
import com.example.therapet.helpers.FakeUserDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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

    private lateinit var repository: UserRepository

    @Before
    fun setup(){
        repository = UserRepository(FakeUserDao())
    }

    //1. Registration - no invalid test needed because invalid details are caught by the UI and register button is disabled until details ARE valid
    @Test
    fun registering_inserts_user_into_db() = runBlocking {
        repository.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")

        val exists = repository.userExists("X328DFSJ108Z")
        assertTrue(exists)
    }

    //2. Logging in with valid details TODO: Implement login state functionality
    @Test
    fun login_with_valid_details() = runBlocking {
        repository.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")

        val result = repository.login("X328DFSJ108Z", "Password_123")
    }

    //3. Logging in with invalid details TODO: Implement login state functionality
    @Test
    fun login_with_invalid_details() = runBlocking {
        val result = repository.login("83248ndsfnskjjesnf838", "invalid")
        assertFalse(result)
    }
}