package com.example.therapet.app.data.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * This session manager stores the logged in user's ID
 * Logging in stores the ID and happens AFTER actual login functionality is a success, logging out sets it to null
 */

class SessionManager{
    private val _currentUserId = MutableStateFlow<String>("")
    val currentUserId: StateFlow<String?> = _currentUserId

    fun login(userId: String) {
        _currentUserId.value = userId
    }

    fun logout(){
        _currentUserId.value = ""
    }
}