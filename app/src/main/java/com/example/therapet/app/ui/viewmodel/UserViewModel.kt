package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.model.toAccountUIModel
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.data.session.SessionManagerContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * User view model - holds the login & registration state
 * (Does NOT know about the fragments, talk to the database directly or show UI)
 * Login and register screens will be wired to the viewmodel (fragments are not needed as I am using compose not XML)
 *
 * With help from code: https://github.com/android/sunflower/tree/main/app/src/main/java/com/google/samples/apps/sunflower/viewmodels
 */

class UserViewModel(
    private val repository: UserRepositoryContract,
    private val sessionManager: SessionManager
) : ViewModel() {

    /**
     * Authentication State
     */
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    private val _therapists = MutableStateFlow<List<AccountUIModel>>(emptyList())
    val therapists: StateFlow<List<AccountUIModel>> = _therapists

    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> = _registerResult


    private val _loggedInRole = MutableStateFlow<UserRole?>(null)
    val loggedInRole: StateFlow<UserRole?> = _loggedInRole

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser

    private val _resetPasswordResult = MutableStateFlow<Boolean?>(null)
    val resetPasswordResult: StateFlow<Boolean?> = _resetPasswordResult

    private val _selectedUser = MutableStateFlow<Pair<AccountUIModel, UserRole>?>(null)
    val selectedUser: StateFlow<Pair<AccountUIModel, UserRole>?> = _selectedUser

    /**
     * Auth methods
     */

    /**
     * Attempts to login user with given credentials
     */
    fun login(
        userid: String,
        password: String
    ) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                repository.login(userid, password)
            }

            if (user != null) {
                sessionManager.login(user.userid, user.role)
                _loggedInRole.value = user.role
                _loginResult.value = true
            } else {
                _loginResult.value = false
            }
        }
    }

    /**
     * Registers a new user and logs them in
     */
    fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ) {
        viewModelScope.launch {
            val userAlreadyExists = withContext(Dispatchers.IO) {
                repository.userExists(userid)
            }

            if (userAlreadyExists) {
                _registerResult.value = false
                return@launch
            }

            withContext(Dispatchers.Default) {
                repository.register(userid, firstname, surname, password)
            }

            val user = withContext(Dispatchers.IO) {
                repository.login(userid, password)
            }

            if (user != null) {
                sessionManager.login(user.userid, user.role)
                _loggedInRole.value = user.role
                _registerResult.value = true
            } else {
                _registerResult.value = false
            }
        }
    }

    /**
     * Deletes account
     */

    fun deleteAccount() {
        val session = sessionManager.session.value ?: return

        viewModelScope.launch {
            repository.deleteUser(session.userid)
            sessionManager.logout()
            _loggedInRole.value = null
            _currentUser.value = null
        }
    }

    /**
     * Logs out current user
     */
    fun logout() {
        sessionManager.logout()
        _loggedInRole.value = null
    }

    /**
     * Clears UI results
     */

    fun clearLoginResult() {
        _loginResult.value = null
    }

    /**
     * Clears registration results
     */
    fun clearRegisterResult() {
        _registerResult.value = null
    }

    /**
     * Verifies user password
     */
    suspend fun verifyPassword(input: String): Boolean = withContext(Dispatchers.IO) {
        val session = sessionManager.session.value ?: return@withContext false
        repository.login(session.userid, input) != null
    }

    /**
     * Loads current users
     */
    fun loadCurrentUser() {
        val session = sessionManager.session.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            _currentUser.value = repository.getUserById(session.userid)
        }
    }

    /**
     * Resets the password
     */
    fun resetPassword(newPassword: String) {
        val session = sessionManager.session.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val success = repository.updatePassword(session.userid, newPassword)
            _resetPasswordResult.value = success
        }
    }

    /**
     * Clears password reset result
     */
    fun clearResetPasswordResult() {
        _resetPasswordResult.value = null
    }

    /**
     * Loads therapists
     */
    fun loadTherapists() {
        viewModelScope.launch {
            _therapists.value =
                repository.getTherapists()
                    .map { it.toAccountUIModel() }
        }
    }
}
