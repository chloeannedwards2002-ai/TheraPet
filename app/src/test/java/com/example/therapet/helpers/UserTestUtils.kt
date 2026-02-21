package com.example.therapet.helpers

import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.repositories.FakeUserRepository

/**
 * Registers a user in the repository and logs them into the session.
 */
suspend fun registerAndLogin(
    repository: FakeUserRepository,
    sessionManager: SessionManager,
    userId: String,
    password: String = "Password_123",
    role: UserRole = UserRole.PATIENT,
    firstname: String = "Test",
    surname: String = "Test"
) {
    repository.register(userId, firstname, surname, password)
    sessionManager.login(userId, role)
}

