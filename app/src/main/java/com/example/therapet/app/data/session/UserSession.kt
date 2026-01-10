package com.example.therapet.app.data.session

import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 10/01/2026
 *
 * Holds the user session
 */

data class UserSession(
    val userid: String,
    val role: UserRole
)