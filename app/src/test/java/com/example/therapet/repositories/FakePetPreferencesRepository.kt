package com.example.therapet.repositories

import com.example.therapet.app.data.repository.contracts.PetPreferencesRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * A fake pet pref repository for tests
 */

class FakePetPreferencesRepository : PetPreferencesRepositoryContract {

    private val _petColourIndex = MutableStateFlow(0)
    override val petColourIndex: Flow<Int> = _petColourIndex

    override suspend fun savePetColour(index: Int){
        _petColourIndex.value = index
    }
}