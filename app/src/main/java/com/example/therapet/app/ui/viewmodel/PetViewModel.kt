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
 * With help from code: https://github.com/android/sunflower/tree/main/app/src/main/java/com/google/samples/apps/sunflower/viewmodels
 */

class PetViewModel(
    private val repository: PetPreferencesRepositoryContract
) : ViewModel() {

    val selectedColourIndex = repository.petColourIndex
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = 0
        )

    fun selectColour(index: Int) {
        viewModelScope.launch {
            repository.savePetColour(index)
        }
    }
}