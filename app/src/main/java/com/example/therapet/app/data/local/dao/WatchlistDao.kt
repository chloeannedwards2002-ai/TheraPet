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

    /**
     * Adds a patient to a therapist's watchlist
     *
     * @param entry the watchlist entity representing the therapist-patient relationship
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToWatchlist(entry: WatchlistEntity)

    /**
     * Retrieves all patient user IDs that are on the therapist's watchlist
     *
     * @param therapistId The unique ID of the therapist
     * @return Flow emitting a list of patient user IDs
     */
    @Query("SELECT patientUserId FROM watchlist_entries WHERE therapistUserId = :therapistId")
    fun getWatchlistPatientIds(therapistId: String): Flow<List<String>>

    /**
     * Removes a specific patient from a therapist's watchlist
     *
     * @param therapistId The unique ID of the therapist
     * @param patientId The unique ID of the patient to remove
     */
    @Query("DELETE FROM watchlist_entries WHERE therapistUserId = :therapistId AND patientUserId = :patientId")
    suspend fun deletePatientFromWatchlist(therapistId: String, patientId: String)
}