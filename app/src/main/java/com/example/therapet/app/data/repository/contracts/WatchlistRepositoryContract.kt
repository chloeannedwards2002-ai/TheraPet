package com.example.therapet.app.data.repository.contracts

import com.example.therapet.app.data.model.AccountUIModel
import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 15/02/2026
 *
 * Contract for managing therapist watchlists
 */
interface WatchlistRepositoryContract {

    /**
     * Adds a patient to the therapist's watchlist
     */
    suspend fun addPatientToWatchlist(
        therapistId: String,
        patientId: String
    )

    /**
     * Returns flow of al patients on a therapist's watchlist
     */
    fun getWatchlistForTherapist(
        therapistId: String
    ): Flow<List<AccountUIModel>>

    /**
     * Removes a patient from a therapists watchlist
     */
    suspend fun removePatientFromWatchlist(
        therapistId: String,
        patientId: String
    )
}