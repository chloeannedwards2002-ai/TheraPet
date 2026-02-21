package com.example.therapet.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import com.example.therapet.app.data.datastore.petCareDataStore
import com.example.therapet.app.data.repository.PetCareStateRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 21/02/2026
 *
 * PetCareStateRepositoryTest
 *
 * Tests the PetCareStateRepository functionality including:
 */

class PetCareStateRepositoryTest {

    private lateinit var context: Context
    private lateinit var repository: PetCareStateRepository

    /**
     * Set up a fresh repository instance before each test.
     * Clears the DataStore to ensure test isolation.
     */
    @Before
    fun setup() = runBlocking {
        context = ApplicationProvider.getApplicationContext()

        // Clear the datastore before each test
        context.petCareDataStore.edit { it.clear() }

        repository = PetCareStateRepository.getInstance(context)
    }

    /**
     * Test 1:
     * Ensure default values are returned when nothing has been saved
     */
    @Test
    fun default_values_are_returned_when_empty() = runBlocking {
        val water = repository.waterLevel.first()
        val food = repository.foodLevel.first()
        val sleep = repository.sleepLevel.first()
        val hibernation = repository.hibernationEnabled.first()

        assertEquals(1f, water)
        assertEquals(1f, food)
        assertEquals(1f, sleep)
        assertEquals(false, hibernation)
    }

    /**
     * Test 2:
     * Saving water level should persist and return the correct value
     */
    @Test
    fun saving_water_level_persists_value() = runBlocking {
        repository.saveWaterLevel(0.5f)

        val water = repository.waterLevel.first()
        assertEquals(0.5f, water)
    }

    /**
     * Test 3:
     * Saving food level should persist and return the correct value
     */
    @Test
    fun saving_food_level_persists_value() = runBlocking {
        repository.saveFoodLevel(0.3f)

        val food = repository.foodLevel.first()
        assertEquals(0.3f, food)
    }

    /**
     * Test 4:
     * Saving sleep level should persist and return the correct value
     */
    @Test
    fun saving_sleep_level_persists_value() = runBlocking {
        repository.saveSleepLevel(0.8f)

        val sleep = repository.sleepLevel.first()
        assertEquals(0.8f, sleep)
    }

    /**
     * Test 5:
     * Saving hibernation state should persist and return the correct value
     */
    @Test
    fun saving_hibernation_state_persists_value() = runBlocking {
        repository.saveHibernationEnabled(true)

        val hibernation = repository.hibernationEnabled.first()
        assertEquals(true, hibernation)
    }
}