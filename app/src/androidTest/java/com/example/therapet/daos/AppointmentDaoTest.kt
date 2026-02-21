package com.example.therapet.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.local.dao.AppointmentDao
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
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
 * AppointmentDaoTest
 *
 * These tests verify the behaviour of the AppointmentDao
 * by testing inserting, updating, deleting and retrieving appointments
 *
 * An in-memory Room database is used to isolate these tests from real data
 * Test users are inserted to satisfy the foreign key constraints
 */

@RunWith(AndroidJUnit4::class)
class AppointmentDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: AppointmentDao

    /**
     * Setup runs before each test:
     * Creates a new in-memory Room database
     * Gets the AppointmentDao from the database
     * Inserts a therapist and patient user to satisfy foreign key constraints
     */
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.appointmentDao()

        val userDao = database.userDao()
        runBlocking {
            // Inserting a therapist user
            userDao.insertUser(
                UserEntity(
                    userid = "1231231231231234",
                    firstname = "Therapist",
                    surname = "One",
                    passwordHash = "hash",
                    salt = "salt",
                    role = UserRole.THERAPIST
                )
            )
            // Inserting a patient user
            userDao.insertUser(
                UserEntity(
                    userid = "123123123123",
                    firstname = "Patient",
                    surname = "One",
                    passwordHash = "hash",
                    salt = "salt",
                    role = UserRole.PATIENT
                )
            )
        }
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
     * Inserting an appointment and retrieving it for a therapist
     */
    @Test
    fun insert_and_retrieve_appointment_for_therapist() = runBlocking {
        val appointment = AppointmentEntity(
            therapistUserId = "1231231231231234",
            patientUserId = "123123123123",
            appointmentDateTime = System.currentTimeMillis(),
            appointmentType = AppointmentType.CONSULTATION,
            isBooked = true
        )

        dao.insertAppointment(appointment)

        val appointments = dao.getAppointmentsForTherapist("1231231231231234").first()

        assertEquals(1, appointments.size)
        assertEquals("123123123123", appointments[0].patientUserId)
        assertEquals(AppointmentType.CONSULTATION, appointments[0].appointmentType)
    }

    /**
     * Updating an existing appointment's type
     */
    @Test
    fun update_appointment_changes__appointment_type() = runBlocking {
        val appointment = AppointmentEntity(
            therapistUserId = "1231231231231234",
            patientUserId = "123123123123",
            appointmentDateTime = System.currentTimeMillis(),
            appointmentType = AppointmentType.CONSULTATION
        )
        dao.insertAppointment(appointment)

        val insertedAppointment = dao.getAppointmentsForTherapist("1231231231231234")
            .first()
            .first()

        val updatedAppointment = insertedAppointment.copy(
            appointmentType = AppointmentType.FOLLOW_UP
        )
        dao.updateAppointment(updatedAppointment)

        val retrieved = dao.getAppointmentsForTherapist("1231231231231234").first()
        assertEquals(AppointmentType.FOLLOW_UP, retrieved[0].appointmentType)
    }

    /**
     * Deleting an appointment
     */
    @Test
    fun delete_appointment_removes_appointment() = runBlocking {
        val appointment = AppointmentEntity(
            therapistUserId = "1231231231231234",
            patientUserId = "123123123123",
            appointmentDateTime = System.currentTimeMillis(),
            appointmentType = AppointmentType.CONSULTATION
        )
        dao.insertAppointment(appointment)

        val insertedAppointment = dao.getAppointmentsForTherapist("1231231231231234")
            .first()
            .first()

        dao.deleteAppointment(insertedAppointment)

        val appointments = dao.getAppointmentsForTherapist("1231231231231234").first()
        assertEquals(0, appointments.size)
    }
}