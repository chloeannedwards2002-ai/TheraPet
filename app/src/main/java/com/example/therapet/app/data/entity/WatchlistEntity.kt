package com.example.therapet.app.data.entity

import androidx.room.Entity

/**
 * @author: Chloe Edwards
 * @date: 15/02/2026
 *
 * The appointment database table
 */

@Entity(
    tableName = "watchlist_entries",
    primaryKeys = ["therapistUserId", "patientUserId"]
)
data class WatchlistEntity(
    val therapistUserId: String,
    val patientUserId: String
)