package com.example.therapet.app.data.model

/**
 * @author: Chloe Edwards
 * @date: 03/02/2026
 *
 * Defines the type of appointment that can be booked
 */

enum class AppointmentType {
    CONSULTATION,
    FOLLOW_UP,
    SESSION;

    fun displayName(): String =
        name.lowercase()
            .replace("_", " ")
            .replaceFirstChar { it.uppercase() }
}