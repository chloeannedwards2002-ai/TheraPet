package com.example.therapet.app.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

/**
 * @author: Chloe Edwards
 * @date: 14/02/2026
 *
 * Pet care data store
 */

val Context.petCareDataStore by preferencesDataStore(name = "pet_care_state")

object PetCareKeys {
    val FOOD = floatPreferencesKey("food_level")
    val WATER = floatPreferencesKey("water_level")
    val SLEEP = floatPreferencesKey("sleep_level")

    val HIBERNATION = booleanPreferencesKey("hibernation_enabled")

    val REMINDERS = booleanPreferencesKey("reminders_enabled")
}