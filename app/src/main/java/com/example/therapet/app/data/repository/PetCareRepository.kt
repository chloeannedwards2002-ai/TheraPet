package com.example.therapet.app.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import com.example.therapet.app.data.datastore.PetCareKeys
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
    }

    override val waterLevel = dataStore.data.map { prefs ->
        prefs[PetCareKeys.WATER] ?: 1f
    }

    override val foodLevel = dataStore.data.map { prefs ->
        prefs[PetCareKeys.FOOD] ?: 1f
    }

    override val hibernationEnabled = dataStore.data.map { prefs ->
        prefs[PetCareKeys.HIBERNATION] ?: false
    }

    override suspend fun saveWaterLevel(value: Float) {
        dataStore.edit { prefs ->
            prefs[PetCareKeys.WATER] = value
        }
    }

    override suspend fun saveFoodLevel(value: Float) {
        dataStore.edit { prefs ->
            prefs[PetCareKeys.FOOD] = value
        }
    }

    override val sleepLevel = dataStore.data.map { prefs ->
        prefs[PetCareKeys.SLEEP] ?: 1f
    }

    override suspend fun saveSleepLevel(value: Float) {
        dataStore.edit { prefs ->
            prefs[PetCareKeys.SLEEP] = value
        }
    }

    override suspend fun saveHibernationEnabled(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[PetCareKeys.HIBERNATION] = value
        }
    }
}