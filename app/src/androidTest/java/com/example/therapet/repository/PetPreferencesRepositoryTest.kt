package com.example.therapet.repository

import PetPreferencesRepository
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 21/02/2026
 *
 * PetPreferencesRepositoryTest
 *
 * Tests the pet preferencecs functionality
 */

class PetPreferencesRepositoryTest {

    private lateinit var context: Context
    private lateinit var repository: PetPreferencesRepository
    private var testUserId = "test_user_123"

    @Before
    fun setup() = runBlocking {
        context = ApplicationProvider.getApplicationContext()

        testUserId = "test_user_${System.currentTimeMillis()}"

        repository = PetPreferencesRepository.getInstance(context, testUserId)
    }

    /**
     * 1. Default value should be 0 if nothing has been saved
     */
    @Test
    fun default_pet_colour_is_zero() = runBlocking {
        val colour = repository.petColourIndex.first()
        assertEquals(0, colour)
    }

    /**
     * 2. Saving a pet colour should persist
     */
    @Test
    fun saving_pet_colour_persists_value() = runBlocking {
        repository.savePetColour(3)

        val colour = repository.petColourIndex.first()
        assertEquals(3, colour)
    }
}