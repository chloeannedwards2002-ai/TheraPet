package com.example.therapet.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.AppointmentRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 21/02/2026
 *
 * Tests the appointments repository functionality
 */
class AppointmentRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var repository: AppointmentRepository

    private lateinit var therapist: UserEntity
    private lateinit var patient: UserEntity

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()
        val appointmentDao = db.appointmentDao()

        repository = AppointmentRepository(appointmentDao, userDao)

        runBlocking {
            // Insert a therapist
            therapist = UserEntity(
                userid = "T123456789",
                firstname = "Therapist",
                surname = "One",
                passwordHash = "hash",
                salt = "salt",
                role = UserRole.THERAPIST
            )
            userDao.insertUser(therapist)

            // Insert a patient
            patient = UserEntity(
                userid = "P123456789",
                firstname = "Patient",
                surname = "One",
                passwordHash = "hash",
                salt = "salt",
                role = UserRole.PATIENT
            )
            userDao.insertUser(patient)
        }
    }

    @After
    fun teardown() {
        db.close()
    }

    /**
     * 1. Create an appointment should insert into the database and assocaite with the right therapist
     */
    @Test
    fun creating_appointment_inserts_it_for_therapist() = runBlocking {
        val time = System.currentTimeMillis()
        repository.createAppointment(
            therapistUserId = therapist.userid,
            dateTimeMillis = time,
            appointmentType = AppointmentType.CONSULTATION
        )

        val appointments = repository.getAppointmentsForTherapist(therapist.userid).first()
        assertEquals(1, appointments.size)
        assertEquals(AppointmentType.CONSULTATION, appointments[0].appointmentType)
        assertEquals(therapist.userid, appointments[0].therapistUserId)
        assertEquals(false, appointments[0].isBooked)
    }

    /**
     * 2. A patient booking an appointment should allow retrieval
     */
    @Test
    fun get_appointments_for_patient_returns_correct_list() = runBlocking {
        val time = System.currentTimeMillis()
        repository.createAppointment(
            therapistUserId = therapist.userid,
            dateTimeMillis = time,
            appointmentType = AppointmentType.SESSION
        )

        // Manually booking the appointment for the patient
        val appointment = repository.getAppointmentsForTherapist(therapist.userid).first().first()
        val bookedAppointment = appointment.copy(
            patientUserId = patient.userid,
            isBooked = true
        )
        repository.updateAppointment(bookedAppointment)

        val patientAppointments = repository.getAppointmentsForPatient(patient.userid).first()
        assertEquals(1, patientAppointments.size)
        assertEquals(patient.userid, patientAppointments[0].patientUserId)
    }

    /**
     * 3. Update the appointment should persist the new appointment type
     */
    @Test
    fun updating_appointment_changes_type() = runBlocking {
        val time = System.currentTimeMillis()
        repository.createAppointment(
            therapistUserId = therapist.userid,
            dateTimeMillis = time,
            appointmentType = AppointmentType.CONSULTATION
        )

        val appointment = repository.getAppointmentsForTherapist(therapist.userid).first().first()
        val updated = appointment.copy(appointmentType = AppointmentType.FOLLOW_UP)
        repository.updateAppointment(updated)

        val retrieved = repository.getAppointmentsForTherapist(therapist.userid).first().first()
        assertEquals(AppointmentType.FOLLOW_UP, retrieved.appointmentType)
    }

    /**
     * 4. Deleting an appointment should remove it from the database
     */
    @Test
    fun deleting_appointment_removes_it() = runBlocking {
        val time = System.currentTimeMillis()
        repository.createAppointment(
            therapistUserId = therapist.userid,
            dateTimeMillis = time,
            appointmentType = AppointmentType.CONSULTATION
        )

        val appointment = repository.getAppointmentsForTherapist(therapist.userid).first().first()
        repository.deleteAppointment(appointment)

        val remaining = repository.getAppointmentsForTherapist(therapist.userid).first()
        assertEquals(0, remaining.size)
    }

    /**
     * 5. Getting patient name should return null if the userId is null and return the patients full name
     */
    @Test
    fun get_patient_name_returns_full_name_or_null() = runBlocking {
        val patientNameNull = repository.getPatientName(null)
        assertEquals(null, patientNameNull)

        val time = System.currentTimeMillis()
        repository.createAppointment(
            therapistUserId = therapist.userid,
            dateTimeMillis = time,
            appointmentType = AppointmentType.CONSULTATION
        )

        val appointment = repository.getAppointmentsForTherapist(therapist.userid).first().first()
        val bookedAppointment = appointment.copy(
            patientUserId = patient.userid,
            isBooked = true
        )
        repository.updateAppointment(bookedAppointment)

        val patientName = repository.getPatientName(patient.userid)
        assertEquals("${patient.firstname} ${patient.surname}", patientName)
    }
}