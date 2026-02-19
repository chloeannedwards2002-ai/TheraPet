package com.example.therapet.app.data.repository

import com.example.therapet.app.data.entity.WatchlistEntity
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.local.dao.WatchlistDao
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class WatchlistRepository(
    private val watchlistDao: WatchlistDao,
    private val userDao: UserDao
) : WatchlistRepositoryContract {

    override suspend fun addPatientToWatchlist(therapistId: String, patientId: String) {
        watchlistDao.addToWatchlist(WatchlistEntity(therapistId, patientId))
    }

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
                                role = it.role
                            )
                        }
                    }
                    emit(accounts)
                }
            }
}