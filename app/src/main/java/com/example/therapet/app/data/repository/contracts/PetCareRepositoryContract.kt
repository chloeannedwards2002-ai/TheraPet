package com.example.therapet.app.data.repository.contracts

import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Pet care repo contract
 */

interface PetCareRepositoryContract {
    val waterLevel: Flow<Float>
    val foodLevel: Flow<Float>
    val sleepLevel: Flow<Float>

    val hibernationEnabled: Flow<Boolean>

    suspend fun saveWaterLevel(value: Float)
    suspend fun saveFoodLevel(value: Float)
    suspend fun saveSleepLevel(value: Float)

    suspend fun saveHibernationEnabled(value: Boolean)
}