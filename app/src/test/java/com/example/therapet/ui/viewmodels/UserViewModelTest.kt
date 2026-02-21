package com.example.therapet.ui.viewmodels

import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.helpers.TestDispatcher
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.repositories.FakeUserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertNull
import com.example.therapet.app.data.model.UserRole
import junit.framework.TestCase.assertNotNull
import com.example.therapet.helpers.registerAndLogin


/**
 * @author: Chloe Edwards
 * @date: 08/01/2026
 *
 * User view model tests
 * 1. Registration is successful and sets register result to true
 * 2. Registration is not successful and register result is set to false
 * 3. When register result is cleared, becomes null
 * 4. With valid login details, login result is true
 * 5. With invalid login details, login result is false
 * 6. When login is done, login result is cleared#
 * 7. registering logs the user in straight away
 */

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcher()

    private lateinit var viewModel: UserViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var repository: FakeUserRepository

    @Before
    fun setup() {
        repository = FakeUserRepository()
        sessionManager = SessionManager()
        viewModel = UserViewModel(repository, sessionManager)
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
    // 3. When register result is cleared, becomes null
    @Test
    fun register_result_clear_is_null() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()
        viewModel.clearRegisterResult()
        assertNull(viewModel.registerResult.value)
    }

    // 4. With valid login details, login result is true
    @Test
    fun valid_login_details_set_login_result_true() = runTest {
        viewModel.register("X328DFSJ108Z", "Bob", "Bobbington", "Password_123")
        advanceUntilIdle()
        viewModel.login("X328DFSJ108Z", "Password_123")
        advanceUntilIdle()
        assertEquals(true, viewModel.loginResult.value)
    }

    // 5. With invalid login details, login result is false
    @Test
    fun invalid_login_details_set_login_result_false() = runTest {
        viewModel.login("X328DFSJ108Z", "invalid")
        advanceUntilIdle()
        assertEquals(false, viewModel.loginResult.value)
    }

    //6. When login is done, login result is cleared
    @Test
    fun login_result_cleared() = runTest {
        viewModel.login("X328DFSJ108Z", "Password_123")
        advanceUntilIdle()
        viewModel.clearLoginResult()
        assertNull(viewModel.loginResult.value)
    }

    // 7. registering logs the user in straight away
    @Test
    fun registering_logs_user_in_automatically() = runTest {
        // now you can use the sessionManager you created in setup()
        viewModel.register("123456789012", "Alice", "Smith", "Password_123")
        advanceUntilIdle()

        assertEquals(true, viewModel.registerResult.value)

        val session = sessionManager.session.value
        assertEquals("123456789012", session?.userid)
        assertEquals(UserRole.PATIENT, session?.role)
    }

    //8. registering as a patient sets the logged in role to patient
    @Test
    fun registering_patient_sets_loggedInRole_to_patient() = runTest {
        viewModel.register(
            userid = "123456123456",
            firstname = "Bob",
            surname = "Bobbington",
            password = "Password_123"
        )
        advanceUntilIdle()

        assertEquals(
            UserRole.PATIENT,
            viewModel.loggedInRole.value
        )
    }

    // 9. registering as a therapist sets the logged in role to therapist
    @Test
    fun registering_therapist_sets_loggedInRole_to_therapist() = runTest {
        viewModel.register(
            userid = "1234567891234567",
            firstname = "Callie",
            surname = "Callaway",
            password = "Password_123"
        )
        advanceUntilIdle()

        assertEquals(
            UserRole.THERAPIST,
            viewModel.loggedInRole.value
        )
    }

    //10. deleting account deletes the user and log outs
    @Test
    fun delete_user_deletes_user_and_logs_out() = runTest {
        val userId = "123454345643"

        registerAndLogin(repository, sessionManager, userId)

        viewModel.deleteAccount()
        advanceUntilIdle()

        assertFalse(repository.userExists(userId))
        assertNull(sessionManager.session.value)
    }

    //11. verifying user password before account deletion returns true
    @Test
    fun verify_password_returns_true_for_correct_password() = runTest{
        val userId = "123456789012"
        val password = "Password_123"

        registerAndLogin(repository, sessionManager, userId, password)

        val result = viewModel.verifyPassword(password)

        assertTrue(result)
    }

    //12. Verify password returns false for incorrect password
    @Test
    fun verify_password_returns_false_for_incorrect_password() = runTest {
        val userId = "123456789012"

        registerAndLogin(repository, sessionManager, userId)

        val result = viewModel.verifyPassword("incorrect")

        assertFalse(result)
    }

    //13. Load current user loads the currently logged in user
    @Test
    fun load_current_user_loads_currently_logged_in_user() = runTest{
        val userId="123456789012"
        val password = "Password_123"

        registerAndLogin(
            repository,
            sessionManager,
            userId,
            password,
            firstname = "Alice",
            surname = "Smith"
        )

        viewModel.loadCurrentUser()
        advanceUntilIdle()

        val user = viewModel.currentUser.value
        assertEquals(userId, user?.userid)
        assertEquals("Alice", user?.firstname)
        assertEquals("Smith", user?.surname)
    }

    // 15. Deleting the account clears the current user
    @Test
    fun delete_account_clears_current_user() = runTest{
        val userId = "123456789012"

        registerAndLogin(repository, sessionManager, userId)

        viewModel.loadCurrentUser()
        advanceUntilIdle()
        assertEquals(userId, viewModel.currentUser.value?.userid)

        viewModel.deleteAccount()
        advanceUntilIdle()

        assertNull(viewModel.currentUser.value)
    }

    //16. Reset password
    @Test
    fun resetPassword_updates_password() = runTest {
        val userId = "123456789012"

        registerAndLogin(repository, sessionManager, userId)

        viewModel.resetPassword("newPassword_123")
        advanceUntilIdle()

        val updatedUser = repository.login(userId, "newPassword_123")
        assertNotNull(updatedUser)
    }
}


