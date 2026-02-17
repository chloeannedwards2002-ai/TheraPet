package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.repository.contracts.PetCareRepositoryContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val tickDelayMs: Long = 100L,
    startLoop: Boolean = true
) : ViewModel() {

    private var persistCounter = 0
    val food: StateFlow<Float> = repository.foodLevel
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.5f)
    val water: StateFlow<Float> = repository.waterLevel
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.5f)

    val sleep: StateFlow<Float> = repository.sleepLevel
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0f)

    private val _isSleeping = MutableStateFlow(false)

    val isHibernating: StateFlow<Boolean> =
        repository.hibernationEnabled
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isSleeping: StateFlow<Boolean> = _isSleeping

    private val decayRate = 0.003f
    private var isActive = true

    init {
       if (startLoop) startDecayLoop()
    }

    private fun startDecayLoop() {
        viewModelScope.launch(dispatcher) {
            while (true) {
                applyDecay()
                delay(tickDelayMs)
            }
        }
    }

    private suspend fun applyDecay() {
        if (!isActive || isHibernating.value) return

        val newFood = max(0f, food.value - decayRate)
        val newWater = max(0f, water.value - decayRate)
        val newSleep = max(0f, sleep.value - 0.0005f)

        persistCounter++

        if (persistCounter >= 20) {
            persistCounter = 0
            repository.saveFoodLevel(newFood)
            repository.saveWaterLevel(newWater)
            repository.saveSleepLevel(newSleep)
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

    // Sleeping
    fun startSleep() {
        if (_isSleeping.value) return

        viewModelScope.launch(dispatcher) {
            _isSleeping.value = true
            isActive = false   // stop decay while sleeping

            val durationMs = 20_000L
            val steps = durationMs / tickDelayMs
            val startValue = sleep.value
            val increment = (1f - startValue) / steps

            repeat(steps.toInt()) {
                val newValue = min(1f, sleep.value + increment)
                repository.saveSleepLevel(newValue)
                repository.saveSleepLevel(1f)
                delay(tickDelayMs)
            }

            repository.saveSleepLevel(1f)
            isActive = true
            _isSleeping.value = false
        }
    }

    fun setActive(active: Boolean) { isActive = active }

    fun setHibernation(enabled: Boolean) {
        viewModelScope.launch {
            repository.saveHibernationEnabled(enabled)
        }
    }
}