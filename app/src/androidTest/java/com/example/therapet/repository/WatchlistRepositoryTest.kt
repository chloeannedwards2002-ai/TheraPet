package com.example.therapet.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.WatchlistRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 21/02/2026
 *
 * WatchlistRepositoryTest
 *
 * Tests the watch list repository functionality
 */

class WatchlistRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var repository: WatchlistRepository

    private lateinit var therapist: UserEntity
    private lateinit var patient: UserEntity

    @Before
    fun setup() = runBlocking {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()
        val watchlistDao = db.watchlistDao()

        repository = WatchlistRepository(watchlistDao, userDao)

        // Insert therapist
        therapist = UserEntity(
            userid = "1231231231231234",
            firstname = "Therapist",
            surname = "One",
            passwordHash = "Password_123",
            salt = "abcdefg",
            role = UserRole.THERAPIST
        )
        userDao.insertUser(therapist)

        // Insert patient
        patient = UserEntity(
            userid = "123123123123",
            firstname = "Patient",
            surname = "One",
            passwordHash = "Password_123",
            salt = "abcdefg",
            role = UserRole.PATIENT
        )
        userDao.insertUser(patient)
    }

    @After
    fun teardown() {
        db.close()
    }

    /**
     * 1. Adding a patient to the watchlist should allow retrieval
     */
    @Test
    fun adding_patient_to_watchlist_returns_account_model() = runBlocking {
        repository.addPatientToWatchlist(therapist.userid, patient.userid)

        val watchlist = repository
            .getWatchlistForTherapist(therapist.userid)
            .first()

        assertEquals(1, watchlist.size)
        assertEquals(patient.userid, watchlist[0].userid)
        assertEquals(
            "${patient.firstname} ${patient.surname}",
            watchlist[0].fullName
        )
    }

    /**
     * 2. Removing a patient should remove them from the watchlist.
     */
    @Test
    fun removing_patient_from_watchlist_removes_entry() = runBlocking {
        repository.addPatientToWatchlist(therapist.userid, patient.userid)
        repository.removePatientFromWatchlist(therapist.userid, patient.userid)

        val watchlist = repository
            .getWatchlistForTherapist(therapist.userid)
            .first()

        assertEquals(0, watchlist.size)
    }
}