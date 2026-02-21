package com.example.therapet.app.data.session

import com.example.therapet.app.data.model.UserRole
import kotlinx.coroutines.flow.StateFlow

/**
 * Contract for a session manager
 */
interface SessionManagerContract {
    /**
     * Returns the currently logged-in users ID
     */
    fun getUserId(): String?

    /**
     * Returns the currently logged-in user's role
     */
    fun getRole(): UserRole?

    /**
     * Exposed stateflow representing the current session
     */
    val session: StateFlow<UserSession?>
}
