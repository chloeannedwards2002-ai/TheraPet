package com.example.therapet.helpers

import com.example.therapet.app.data.session.SessionManager

class FakeSessionManager(
    private val userId: String
) : SessionManager {

    override fun getUserId(): String? = userId

    override fun getRole(): UserRole? = UserRole.PATIENT

    override val session = MutableStateFlow(null)
}