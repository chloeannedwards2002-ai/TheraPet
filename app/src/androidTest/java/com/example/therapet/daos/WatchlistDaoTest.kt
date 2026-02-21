package com.example.therapet.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.therapet.app.data.entity.WatchlistEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.local.dao.WatchlistDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author: Chloe Edwards
 * @date: 21/02/2026
 *
 * WatchlistDaoTest
 *
 * These tests verify that the WatchlistDao performs as expected. The DAO
 * allows the therapists to manage their watchlist of patients
 *
 *  * An in-memory Room database is used to isolate these tests from real data
 *  * Test users are inserted to satisfy the foreign key constraints
 */

@RunWith(AndroidJUnit4::class)
class WatchlistDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: WatchlistDao


    /**
     * Setup runs before each test:
     * Creates a new in-memory Room database
     * Gets the WatchlistDao from the database
     * Inserts a therapist and patient user to satisfy foreign key constraints
     */
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.watchlistDao()
    }

    /**
     * Teardown runs after each test
     * This closes the database to free the resources
    */
    @After
    fun teardown() {
        database.close()
    }

    /**
     * Adding a patient to a therapist's watchlist and retrieving it after
     */
    @Test
    fun add_and_retrieve_watchlist_patient() = runBlocking {
        val entry = WatchlistEntity(
            therapistUserId = "T123",
            patientUserId = "P123"
        )

        dao.addToWatchlist(entry)

        val patientIds = dao.getWatchlistPatientIds("T123").first()

        assertEquals(1, patientIds.size)
        assertEquals("P123", patientIds[0])
    }

    /**
     * Adding a duplicate patient to the same therapist watchlist is
     * ignored because of the primary key constraint in the database
     */
    @Test
    fun adding_duplicate_patient_is_ignored() = runBlocking {
        val entry = WatchlistEntity(
            therapistUserId = "T123",
            patientUserId = "P123"
        )

        dao.addToWatchlist(entry)
        dao.addToWatchlist(entry)

        val patientIds = dao.getWatchlistPatientIds("T123").first()

        assertEquals(1, patientIds.size)
    }

    /**
     * Deleting a patient from the watchlist
     */
    @Test
    fun delete_patient_from_watchlist_removes_it() = runBlocking {
        val entry = WatchlistEntity(
            therapistUserId = "T456",
            patientUserId = "P456"
        )

        dao.addToWatchlist(entry)

        dao.deletePatientFromWatchlist("T456", "P456")

        val patientIds = dao.getWatchlistPatientIds("T456").first()
        assertEquals(0, patientIds.size)
    }
}