package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.repository.contracts.PetCareRepositoryContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Pet care view model
 */

class PetCareViewModel(
    private val repository: PetCareRepositoryContract,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val tickDelayMs: Long = 100L,
    startLoop: Boolean = true
) : ViewModel() {

    val food: StateFlow<Float> = repository.foodLevel
        .stateIn(viewModelScope, SharingStarted.Eagerly, 1f)
    val water: StateFlow<Float> = repository.waterLevel
        .stateIn(viewModelScope, SharingStarted.Eagerly, 1f)

    private val decayRate = 0.001f
    private var isActive = true

    init {
       if (startLoop) startDecayLoop()
    }

    private fun startDecayLoop() {
        viewModelScope.launch(dispatcher) {
            while (true) {
                applyDecay()
                kotlinx.coroutines.delay(tickDelayMs)
            }
        }
    }

    fun applyDecay() {
        if (isActive) {
            val newFood = max(0f, food.value - decayRate)
            val newWater = max(0f, water.value - decayRate)

            // Save back to repository
            viewModelScope.launch { repository.saveFoodLevel(newFood) }
            viewModelScope.launch { repository.saveWaterLevel(newWater) }
        }
    }

    fun increaseFood(amount: Float = 0.1f) {
        val newValue = min(1f, food.value + amount)
        viewModelScope.launch { repository.saveFoodLevel(newValue) }
    }

    fun increaseWater(amount: Float = 0.1f) {
        val newValue = min(1f, water.value + amount)
        viewModelScope.launch { repository.saveWaterLevel(newValue) }
    }

    fun setActive(active: Boolean) { isActive = active }
}