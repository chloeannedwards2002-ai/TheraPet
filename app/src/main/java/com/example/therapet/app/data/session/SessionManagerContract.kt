package com.example.therapet.app.data.session

import com.example.therapet.app.data.model.UserRole
import kotlinx.coroutines.flow.StateFlow

interface SessionManagerContract {
    fun getUserId(): String?
    fun getRole(): UserRole?
    val session: StateFlow<UserSession?>
}
