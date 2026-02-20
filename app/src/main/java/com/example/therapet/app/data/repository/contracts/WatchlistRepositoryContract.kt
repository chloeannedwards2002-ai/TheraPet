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

    suspend fun addPatientToWatchlist(
        therapistId: String,
        patientId: String
    )

    fun getWatchlistForTherapist(
        therapistId: String
    ): Flow<List<AccountUIModel>>

    suspend fun removePatientFromWatchlist(
        therapistId: String,
        patientId: String
    )
}