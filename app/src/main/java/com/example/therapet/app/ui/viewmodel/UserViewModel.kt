package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract
import com.example.therapet.app.data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * User view model - holds the login & registration state
 * (Does NOT know about the fragments, talk to the database directly or show UI)
 * Login and register screens will be wired to the viewmodel (fragments are not needed as I am using compose not XML)
 */

class UserViewModel(
    private val repository: UserRepositoryContract,
    private val sessionManager: SessionManager
) : ViewModel() {


    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult


    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> = _registerResult


    private val _loggedInRole = MutableStateFlow<UserRole?>(null)
    val loggedInRole: StateFlow<UserRole?> = _loggedInRole

    // Authentication

    fun login(
        userid: String,
        password: String
    ) {
        viewModelScope.launch {
            val user = repository.login(userid, password)

            if (user != null) {
                _loginResult.value = true
                sessionManager.login(user.userid, user.role)
                _loggedInRole.value = user.role
            } else {
                _loginResult.value = false
            }
        }
    }

    fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ) {
        viewModelScope.launch {
            // Check if user already exists
            if (repository.userExists(userid)) {
                _registerResult.value = false
                return@launch
            }

            repository.register(userid, firstname, surname, password)

            val user = repository.login(userid, password)
            if (user != null) {
                sessionManager.login(user.userid, user.role)
                _loggedInRole.value = user.role
                _registerResult.value = true
            } else {
                _registerResult.value = false
            }
        }
    }

    // Account management

    fun deleteAccount() {
        val session = sessionManager.session.value ?: return

        viewModelScope.launch {
            repository.deleteUser(session.userid)
            sessionManager.logout()
            _loggedInRole.value = null
        }
    }

    fun logout() {
        sessionManager.logout()
        _loggedInRole.value = null
    }

    // Clearing UI results

    fun clearLoginResult() {
        _loginResult.value = null
    }

    fun clearRegisterResult() {
        _registerResult.value = null
    }

    // Verifies the user's password
    fun verifyPassword(input: String): Boolean {
        val session = sessionManager.session.value ?: return false
        return runBlocking {
            repository.login(session.userid, input) != null
        }
    }
}
