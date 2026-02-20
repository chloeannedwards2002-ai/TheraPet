package com.example.therapet.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.therapet.app.data.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 15/02/2026
 *
 * Watchlist access object
 */

@Dao
interface WatchlistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToWatchlist(entry: WatchlistEntity)

    @Query("SELECT patientUserId FROM watchlist_entries WHERE therapistUserId = :therapistId")
    fun getWatchlistPatientIds(therapistId: String): Flow<List<String>>

    @Query("DELETE FROM watchlist_entries WHERE therapistUserId = :therapistId AND patientUserId = :patientId")
    suspend fun deletePatientFromWatchlist(therapistId: String, patientId: String)
}