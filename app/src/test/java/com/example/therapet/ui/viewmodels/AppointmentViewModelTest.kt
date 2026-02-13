package com.example.therapet.ui.viewmodels

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.helpers.FakeSessionManager
import com.example.therapet.helpers.TestDispatcher
import com.example.therapet.repositories.FakeAppointmentRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 02/02/2026
 *
 * Appointment view model tests
 */

class AppointmentViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcher()

    private lateinit var viewModel: AppointmentViewModel
    private lateinit var repository: FakeAppointmentRepository
    private lateinit var sessionManager: SessionManager

    @Before
    fun setup() {
        repository = FakeAppointmentRepository()
        sessionManager = SessionManager()

        // log in as a therapist
        sessionManager.login(
            userid = "1234567890123456",
            role = UserRole.THERAPIST
        )

        viewModel = AppointmentViewModel(
            repository = repository,
            sessionManager = sessionManager
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun creating_appointment_stores_for_therapist() = runTest {
        val dateTime = 1_000_000_000L

        viewModel.addAppointment(
            dateTime,
            AppointmentType.SESSION
        )

        advanceUntilIdle()

        val appointments = viewModel
            .getAppointmentsForTherapist()
            .first()

        assertEquals(1, appointments.size)
        assertEquals(dateTime, appointments.first().appointmentDateTime)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updating_appointment_changes_time() = runTest {
        viewModel.addAppointment(1000L, AppointmentType.SESSION)
        advanceUntilIdle()

        val original =
            viewModel.getAppointmentsForTherapist().first().first()

        val updated =
            original.copy(appointmentDateTime = 2000L)

        viewModel.updateAppointment(updated)
        advanceUntilIdle()

        val result =
            viewModel.getAppointmentsForTherapist().first().first()

        assertEquals(2000L, result.appointmentDateTime)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleting_appointment_removes_it() = runTest {
        viewModel.addAppointment(1000L, AppointmentType.SESSION)
        advanceUntilIdle()

        val appointment =
            viewModel.getAppointmentsForTherapist().first().first()

        viewModel.deleteAppointment(appointment)
        advanceUntilIdle()

        val appointments =
            viewModel.getAppointmentsForTherapist().first()

        assertTrue(appointments.isEmpty())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun patient_booking_sets_to_isBooked_and_patientUserId() = runTest {

        // Therapist creates appointment
        sessionManager.login(
            userid = "therapist123",
            role = UserRole.THERAPIST
        )

        viewModel.addAppointment(1000L, AppointmentType.SESSION)
        advanceUntilIdle()

        val createdAppointment =
            viewModel.getAppointmentsForTherapist().first().first()

        // Patient logs in
        sessionManager.login(
            userid = "patient999",
            role = UserRole.PATIENT
        )

        viewModel.bookAppointment(createdAppointment)
        advanceUntilIdle()

        // IMPORTANT: Fetch appointments for therapist, not patient
        val updatedAppointments =
            viewModel
                .getAppointmentsForTherapistById("therapist123", null)
                .first()

        val updated =
            updatedAppointments.first { it.appointmentId == createdAppointment.appointmentId }

        assertTrue(updated.isBooked)
        assertEquals("patient999", updated.patientUserId)
    }

    @Test
    fun getAppointmentsForPatientReturnsAppointments() = runTest {

        val patientId = "patient123"

        val repository = FakeAppointmentRepository()

        //Create appt
        repository.createAppointment(
            therapistUserId = "therapist1",
            dateTimeMillis = 123456789L,
            appointmentType = AppointmentType.FOLLOW_UP
        )

        //Book manually
        repository.updateAppointment(
            AppointmentEntity(
                appointmentId = 1,
                therapistUserId = "therapist1",
                appointmentDateTime = 123456789L,
                appointmentType = AppointmentType.FOLLOW_UP,
                patientUserId = patientId,
                isBooked = true
            )
        )

        val sessionManager = FakeSessionManager(patientId)

        val viewModel = AppointmentViewModel(
            repository = repository,
            sessionManager = sessionManager
        )

        val result = viewModel.getAppointmentsForPatient().first()

        assertEquals(1, result.size)
        assertEquals(patientId, result.first().patientUserId)
    }

    @Test
    fun bookAppointmentSetsIsBookedTrueAndPatientId() = runTest {

        val patientId = "patient123"

        val repository = FakeAppointmentRepository()

        // Create empty appointment
        repository.createAppointment(
            therapistUserId = "therapist1",
            dateTimeMillis = 123456789L,
            appointmentType = AppointmentType.FOLLOW_UP
        )

        val sessionManager = FakeSessionManager(patientId)

        val viewModel = AppointmentViewModel(
            repository = repository,
            sessionManager = sessionManager
        )

        // get created appointment
        val appointment = repository
            .getAppointmentsForTherapist("therapist1")
            .first()
            .first()

        //Book appointment
        viewModel.bookAppointment(appointment)

        //Check updated value
        val updated = repository
            .getAppointmentsForPatient(patientId)
            .first()

        assertEquals(1, updated.size)
        assertTrue(updated.first().isBooked)
        assertEquals(patientId, updated.first().patientUserId)
    }


}
