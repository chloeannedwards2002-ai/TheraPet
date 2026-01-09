package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.repository.UserRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * User view model - holds the login & registration state
 * (Does NOT know about the fragments, talk to the database directly or show UI)
 * Login and register screens will be wired to the viewmodel (fragments are not needed as I am using compose not XML)
 */

class UserViewModel(
    private val repository: UserRepositoryContract
) : ViewModel() {
//_loginResult is mutable and private, loginResult is read-only and public
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult
//same here for register
    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> = _registerResult


    // gets called by the UI and launches the viewmodel lifecycle coroutine (also updates state)
    fun login(
        userid: String,
        password: String){
        viewModelScope.launch{
            _loginResult.value = repository.login(userid, password)
        }
    }

    fun register(
        userid: String,
        firstname: String,
        surname: String,
        password: String
    ) {
        viewModelScope.launch {
// check if user exists
            if (repository.userExists(userid)) {
                _registerResult.value = false
                return@launch
            }

            repository.register(userid, firstname, surname, password)
            _registerResult.value = true
        }
    }

    fun clearLoginResult() {
        _loginResult.value = null
    }

    fun clearRegisterResult() {
        _registerResult.value = null
    }
}
