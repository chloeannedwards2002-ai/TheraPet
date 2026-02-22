package com.example.therapet.app.data.repository.contracts

import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Pet care repo contract
 */

interface PetCareRepositoryContract {
    /**
     * Current water, food and sleep level of the pet
     */
    val waterLevel: Flow<Float>
    val foodLevel: Flow<Float>
    val sleepLevel: Flow<Float>

    val remindersEnabled: Flow<Boolean>

    /**
     * Boolean indicating if hibernation mode is enabled
     */
    val hibernationEnabled: Flow<Boolean>

    /**
     * Saves the water, food and sleep level of the pet
     */
    suspend fun saveWaterLevel(value: Float)
    suspend fun saveFoodLevel(value: Float)
    suspend fun saveSleepLevel(value: Float)

    /**
     * Saves hibernation state
     */
    suspend fun saveHibernationEnabled(value: Boolean)

    /**
     * Manages reminders from the pet
     */
    suspend fun saveRemindersEnabled(value: Boolean)

}