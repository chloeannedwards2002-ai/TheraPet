package com.example.therapet.app.data.repository.contracts

import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Pet repo contract (abstraction)
 */

interface PetPreferencesRepositoryContract {

    val petColourIndex: Flow<Int>

    suspend fun savePetColour(index: Int)
}