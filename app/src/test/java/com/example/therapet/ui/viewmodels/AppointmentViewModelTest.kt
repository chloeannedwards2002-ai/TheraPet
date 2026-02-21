package com.example.therapet.ui.viewmodels

import FakeWatchlistRepository
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
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
import com.example.therapet.helpers.createAppointmentViewModel
import com.example.therapet.helpers.createPatientSession

/**
 *  * @author: Chloe Edwards
 *  * @date: 21/02/2026
 *  *
 *  * WatchlistRepositoryTest
 *  *
 *  * Tests the business logic inside AppointmentViewModel
 */

class AppointmentViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcher()

    private lateinit var viewModel: AppointmentViewModel
    private lateinit var repository: FakeAppointmentRepository
    private lateinit var sessionManager: FakeSessionManager
    private lateinit var watchlistRepository: FakeWatchlistRepository

    // Test IDs
    private val therapistId = "therapist1"
    private val patientId = "patient1"

    @Before
    fun setup() {
        repository = FakeAppointmentRepository()
        watchlistRepository = FakeWatchlistRepository()

        sessionManager = FakeSessionManager(
            initialUserId = therapistId,
            initialRole = UserRole.THERAPIST
        )

        viewModel = createAppointmentViewModel(
            repository,
            sessionManager,
            watchlistRepository
        )
    }

    private fun switchToPatient() {
        sessionManager = createPatientSession(patientId)

        viewModel = createAppointmentViewModel(
            repository,
            sessionManager,
            watchlistRepository
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun creating_appointment_stores_for_therapist() = runTest {
        val dateTime = 1_000_000_000L

        viewModel.addAppointment(dateTime, AppointmentType.SESSION)
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
        val therapistId = "therapist123"

        repository.createAppointment(
            therapistUserId = therapistId,
            dateTimeMillis = 1000L,
            appointmentType = AppointmentType.SESSION
        )

        switchToPatient()

        val appointment = repository
            .getAppointmentsForTherapist(therapistId)
            .first()
            .first()

        viewModel.bookAppointment(appointment)
        advanceUntilIdle()

        val updatedAppointments =
            repository.getAppointmentsForPatient(patientId).first()

        val updated = updatedAppointments.first {
            it.appointmentId == appointment.appointmentId
        }

        assertTrue(updated.isBooked)
        assertEquals(patientId, updated.patientUserId)
    }

    @Test
    fun getAppointmentsForPatientReturnsAppointments() = runTest {
        repository.createAppointment(
            therapistUserId = therapistId,
            dateTimeMillis = 123456789L,
            appointmentType = AppointmentType.FOLLOW_UP
        )

        val appointment =
            repository.getAppointmentsForTherapist(therapistId)
                .first()
                .first()

        repository.updateAppointment(
            appointment.copy(
                patientUserId = patientId,
                isBooked = true
            )
        )

        switchToPatient()

        val result = viewModel.getAppointmentsForPatient().first()

        assertEquals(1, result.size)
        assertEquals(patientId, result.first().patientUserId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cancelAppointment_setsIsBookedFalse_andClearsPatientId() = runTest {
        repository.createAppointment(
            therapistUserId = therapistId,
            dateTimeMillis = 1000L,
            appointmentType = AppointmentType.SESSION
        )

        switchToPatient()

        val appointment = repository
            .getAppointmentsForTherapist(therapistId)
            .first()
            .first()

        viewModel.bookAppointment(appointment)
        advanceUntilIdle()

        viewModel.cancelAppointment(appointment)
        advanceUntilIdle()

        val updated = repository
            .getAppointmentsForTherapist(therapistId)
            .first()
            .first()

        assertTrue(!updated.isBooked)
        assertEquals(null, updated.patientUserId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cancelAppointment_removesFromPatientAppointments() = runTest {
        repository.createAppointment(
            therapistUserId = therapistId,
            dateTimeMillis = 1000L,
            appointmentType = AppointmentType.SESSION
        )

        switchToPatient()

        val appointment = repository
            .getAppointmentsForTherapist(therapistId)
            .first()
            .first()

        viewModel.bookAppointment(appointment)
        advanceUntilIdle()

        assertEquals(1, viewModel.getAppointmentsForPatient().first().size)

        viewModel.cancelAppointment(appointment)
        advanceUntilIdle()

        val patientAppointments =
            viewModel.getAppointmentsForPatient().first()

        assertTrue(patientAppointments.isEmpty())
    }
}