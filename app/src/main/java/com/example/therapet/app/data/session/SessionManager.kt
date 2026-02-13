package com.example.therapet.app.data.session

import com.example.therapet.app.data.model.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * This session manager stores the logged in user's ID
 * Logging in stores the ID and happens AFTER actual login functionality is a success, logging out sets it to null
 */

class SessionManager {

    private val _session = MutableStateFlow<UserSession?>(null)
    val session: StateFlow<UserSession?> = _session

    fun login(userid: String, role: UserRole) {
        _session.value = UserSession(userid, role)
    }

    fun logout() {
        _session.value = null
    }

    fun isLoggedIn(): Boolean = _session.value != null

    fun getUserId(): String? = _session.value?.userid

    fun getRole(): UserRole? = _session.value?.role
}