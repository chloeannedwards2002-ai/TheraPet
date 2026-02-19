package com.example.therapet.helpers

import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManagerContract
import com.example.therapet.app.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeSessionManager(
    initialUserId: String? = null,
    initialRole: UserRole? = null
) : SessionManagerContract {

    private val _session =
        MutableStateFlow<UserSession?>(
            if (initialUserId != null && initialRole != null)
                UserSession(initialUserId, initialRole)
            else null
        )

    override val session: StateFlow<UserSession?> = _session

    fun login(userid: String, role: UserRole) {
        _session.value = UserSession(userid, role)
    }

    fun logout() {
        _session.value = null
    }

    override fun getUserId(): String? = _session.value?.userid

    override fun getRole(): UserRole? = _session.value?.role
}