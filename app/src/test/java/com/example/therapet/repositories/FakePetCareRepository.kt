package com.example.therapet.repositories

import com.example.therapet.app.data.repository.contracts.PetCareRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakePetCareRepository : PetCareRepositoryContract {

    private val _food = MutableStateFlow(1f)
    private val _water = MutableStateFlow(1f)

    override val foodLevel: StateFlow<Float> get() = _food.asStateFlow()
    override val waterLevel: StateFlow<Float> get() = _water.asStateFlow()

    override suspend fun saveFoodLevel(value: Float) {
        _food.value = value.coerceIn(0f, 1f)
    }

    override suspend fun saveWaterLevel(value: Float) {
        _water.value = value.coerceIn(0f, 1f)
    }
}