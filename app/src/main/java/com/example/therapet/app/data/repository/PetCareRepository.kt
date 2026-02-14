package com.example.therapet.app.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import com.example.therapet.app.data.datastore.petCareDataStore
import com.example.therapet.app.data.repository.contracts.PetCareRepositoryContract
import kotlinx.coroutines.flow.map

class PetCareStateRepository private constructor(
    context: Context
) : PetCareRepositoryContract {

    private val dataStore = context.petCareDataStore

    companion object {
        private var INSTANCE: PetCareStateRepository? = null

        fun getInstance(context: Context): PetCareStateRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PetCareStateRepository(context).also { INSTANCE = it }
            }
        }

        // Keys
        private val WATER_LEVEL_KEY = floatPreferencesKey("water_level")
        private val FOOD_LEVEL_KEY = floatPreferencesKey("food_level")
    }

    override val waterLevel = dataStore.data.map { prefs ->
        prefs[WATER_LEVEL_KEY] ?: 1f
    }

    override val foodLevel = dataStore.data.map { prefs ->
        prefs[FOOD_LEVEL_KEY] ?: 1f
    }

    override suspend fun saveWaterLevel(value: Float) {
        dataStore.edit { prefs ->
            prefs[WATER_LEVEL_KEY] = value
        }
    }

    override suspend fun saveFoodLevel(value: Float) {
        dataStore.edit { prefs ->
            prefs[FOOD_LEVEL_KEY] = value
        }
    }
}