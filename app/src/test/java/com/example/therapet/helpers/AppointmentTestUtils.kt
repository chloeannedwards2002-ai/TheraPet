package com.example.therapet.helpers

import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.repositories.FakeAppointmentRepository
import FakeWatchlistRepository

/**
 * Utils for appointment view model tests
 */

/**
 * Creates a new AppointmentViewModel with the provided dependencies.
 */
fun createAppointmentViewModel(
    repository: FakeAppointmentRepository,
    sessionManager: FakeSessionManager,
    watchlistRepository: FakeWatchlistRepository
): AppointmentViewModel {
    return AppointmentViewModel(
        repository = repository,
        sessionManager = sessionManager,
        watchlistRepository = watchlistRepository
    )
}

/**
 * Creates a FakeSessionManager for a patient user.
 */
fun createPatientSession(patientId: String): FakeSessionManager {
    return FakeSessionManager(
        initialUserId = patientId,
        initialRole = UserRole.PATIENT
    )
}