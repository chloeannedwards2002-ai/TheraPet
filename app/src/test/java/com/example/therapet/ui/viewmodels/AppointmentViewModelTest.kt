package com.example.therapet.ui.viewmodels

import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
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
    }
}