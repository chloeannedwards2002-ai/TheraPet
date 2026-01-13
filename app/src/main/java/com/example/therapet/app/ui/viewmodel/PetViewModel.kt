package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.repository.contracts.PetPreferencesRepositoryContract
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Pet view model
 */

class PetViewModel(
    private val repository: PetPreferencesRepositoryContract
) : ViewModel() {

    val selectedColourIndex = repository.petColourIndex
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    fun selectColour(index: Int) {
        viewModelScope.launch {
            repository.savePetColour(index)
        }
    }
}