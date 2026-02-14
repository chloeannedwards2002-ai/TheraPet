package com.example.therapet.helpers

import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManagerContract
import com.example.therapet.app.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSessionManager(private val userId: String) : SessionManagerContract {
    override val session = MutableStateFlow(UserSession(userId, UserRole.PATIENT))
    fun login(userid: String, role: UserRole) {}
    fun logout() {}
    override fun getUserId(): String? = userId
    override fun getRole(): UserRole? = UserRole.PATIENT
}