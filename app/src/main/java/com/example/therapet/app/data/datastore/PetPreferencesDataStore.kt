package com.example.therapet.app.data.datastore
import androidx.datastore.preferences.preferencesDataStore

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey


/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Pet preferences data store
 */

val Context.petDataStore by preferencesDataStore(
    name = "pet_preferences"
)

object PetPreferencesKeys {
    val PET_COLOUR_INDEX = intPreferencesKey("pet_colour_index")
}
