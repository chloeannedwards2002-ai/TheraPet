package com.example.therapet.viewmodels

import com.example.therapet.helpers.TestDispatcher
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.repositories.FakeUserRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertNull

/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * User view model tests
 * 1. Registration is successful and sets register result to true
 * 2. Registration is not successful and register result is set to false
 * 3. When register result is cleared, becomes null
 * 4.  With valid login details, login result is true
 * 5. With invalid login details, login result is false
 * 6. When login is done, login result is cleared
 */

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get: Rule
    val dispatcherRule = TestDispatcher()

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup(){
        val repository = FakeUserRepository()
        viewModel = UserViewModel(repository)
    }

    //1. Registration is successful and sets register result to true
    @Test
    fun successful_register_gives_register_result_true() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()

        assertEquals(true, viewModel.registerResult.value)
    }

    //2. Registration is not successful and register result is set to false
    @Test
    fun register_existing_userid_gives_register_result_false() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()

        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()

        assertEquals(false, viewModel.registerResult.value)
    }

    //3. When register result is cleared, becomes null
    @Test
    fun register_result_clear_is_null() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()

        viewModel.clearRegisterResult()
        assertNull(viewModel.registerResult.value)
    }

    @Test
    fun valid_login_details_set_login_result_true() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()

        viewModel.login("X328DFSJ108Z", "Password_123")
        advanceUntilIdle()

        assertEquals(true, viewModel.loginResult.value)
    }

    @Test
    fun invalid_login_details_set_login_result_false() = runTest {
        viewModel.login("X328DFSJ108Z", "invalid")
        advanceUntilIdle()

        assertEquals(false, viewModel.loginResult.value)
    }

    @Test
    fun login_result_cleared() = runTest {
        viewModel.login("X328DFSJ108Z", "Password_123")
        advanceUntilIdle()

        viewModel.clearLoginResult()
        assertNull(viewModel.loginResult.value)
    }
}

