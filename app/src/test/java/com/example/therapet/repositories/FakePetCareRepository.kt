package com.example.therapet.repositories

import com.example.therapet.app.data.repository.contracts.PetCareRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author: Chloe Edwards
 * @date: 02/02/2026
 *
 * A fake pet care repository for tests
 */

class FakePetCareRepository : PetCareRepositoryContract {

    private val _food = MutableStateFlow(1f)
    private val _water = MutableStateFlow(1f)
    private val _sleep = MutableStateFlow(0f)
    private val _hibernation = MutableStateFlow(false)

    override val foodLevel: Flow<Float> = _food
    override val waterLevel: Flow<Float> = _water
    override val sleepLevel: Flow<Float> = _sleep
    override val hibernationEnabled: Flow<Boolean> = _hibernation

    override suspend fun saveFoodLevel(value: Float) {
        _food.value = value
    }

    override suspend fun saveWaterLevel(value: Float) {
        _water.value = value
    }

    override suspend fun saveSleepLevel(value: Float) {
        _sleep.value = value
    }

    override suspend fun saveHibernationEnabled(value: Boolean) {
        _hibernation.value = value
    }
}