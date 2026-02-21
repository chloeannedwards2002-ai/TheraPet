package com.example.therapet.app.data.repository

import com.example.therapet.app.data.entity.WatchlistEntity
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.local.dao.WatchlistDao
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

/**
 * Implementation of WatchlistRepositoryContract
 */
class WatchlistRepository(
    private val watchlistDao: WatchlistDao,
    private val userDao: UserDao
) : WatchlistRepositoryContract {

    /**
     * Adds a patient to a therapist's watchlist
     *
     * @param therapistId The ID of the therapist
     * @param patientId the ID of the patient to add
     */
    override suspend fun addPatientToWatchlist(therapistId: String, patientId: String) {
        watchlistDao.addToWatchlist(WatchlistEntity(therapistId, patientId))
    }

    /**
     * Retrieves the full watchlist for a therapist as a Flow
     *
     * @param therapistId the ID of the therapist
     * @return Flow emitting a list of accountUImodel objects
     */
    override fun getWatchlistForTherapist(
        therapistId: String
    ): Flow<List<AccountUIModel>> =
        watchlistDao.getWatchlistPatientIds(therapistId)
            .flatMapLatest { patientIds ->
                flow {
                    val accounts = patientIds.mapNotNull { id ->
                        val user = userDao.getUserById(id)
                        user?.let {
                            AccountUIModel(
                                userid = it.userid,
                                fullName = "${it.firstname} ${it.surname}",
                                role = it.role,
                                lastLoginMillis = it.lastLoginMillis
                            )
                        }
                    }
                    emit(accounts)
                }
            }

    /**
     * Removes a patient from a therapist's watchlist
     *
     * @param therapistId the ID of the therapist
     * @param patientId the ID of the patient to remove
     */
    override suspend fun removePatientFromWatchlist(therapistId: String, patientId: String) {
        watchlistDao.deletePatientFromWatchlist(therapistId, patientId)
    }
}