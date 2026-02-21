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

class SessionManager : SessionManagerContract {

    private val _session = MutableStateFlow<UserSession?>(null)
    override val session: StateFlow<UserSession?> = _session

    /**
     * Logs in a user by storing their ID and role in the session
     *
     * @param userid the unique ID of the logged in user
     *@param role The tole of the user (PATIENT OR THERAPIST)
     */
    fun login(userid: String, role: UserRole) {
        _session.value = UserSession(userid, role)
    }

    /**
     * Logs out the user by clearing the session
     */
    fun logout() {
        _session.value = null
    }

    /**
     * Retrieves the current user's ID
     */
    override fun getUserId(): String? = _session.value?.userid

    /**
     * Retrieves the current user's role
     */
    override fun getRole(): UserRole? = _session.value?.role
}