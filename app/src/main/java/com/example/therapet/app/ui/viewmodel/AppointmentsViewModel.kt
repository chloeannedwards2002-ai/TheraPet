package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.AppointmentRepositoryContract
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import com.example.therapet.app.data.session.SessionManagerContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.time.YearMonth
import com.example.therapet.app.data.util.toMillisRange

/**
 * Viewmodel for managing appointments for therapists and patients
 * Handles creation, booking, updating, deletion and retrieval
 */

class AppointmentViewModel(
    private val repository: AppointmentRepositoryContract,
    private val sessionManager: SessionManagerContract,
    private val _selectedDateMillis: MutableStateFlow<Long?> = MutableStateFlow<Long?>(null),
    private val watchlistRepository: WatchlistRepositoryContract,

    ) : ViewModel() {

    /**
     * Creating a new appointment
     */
    fun addAppointment(
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    ) {
        val session = sessionManager.session.value ?: return

        viewModelScope.launch {
            repository.createAppointment(
                therapistUserId = session.userid,
                dateTimeMillis = dateTimeMillis,
                appointmentType = appointmentType
            )
        }
    }

    /**
     * Patient booking an appointment
     */
    fun bookAppointment(appointment: AppointmentEntity) {
        if (appointment.isBooked) return

        val currentUserId = sessionManager.getUserId()
        val currentRole = sessionManager.getRole()

        if (currentUserId == null || currentRole != UserRole.PATIENT) return

        viewModelScope.launch {
            repository.updateAppointment(
                appointment.copy(
                    isBooked = true,
                    patientUserId = currentUserId
                )
            )

            watchlistRepository.addPatientToWatchlist(
                therapistId = appointment.therapistUserId,
                patientId = currentUserId
            )
        }
    }

    /**
     * Update appointment details
     */
    fun updateAppointment(appointment: AppointmentEntity) {
        viewModelScope.launch {
            repository.updateAppointment(appointment)
        }
    }

    /**
     * Delete appointment
     */
    fun deleteAppointment(appointment: AppointmentEntity) {
        viewModelScope.launch {
            repository.deleteAppointment(appointment)
        }
    }

    /**
     * Get al appts for currently logged in therapist
     */
    fun getAppointmentsForTherapist(): Flow<List<AppointmentEntity>> {
        val therapistId = sessionManager.session.value?.userid
            ?: return flowOf(emptyList())

        return repository.getAppointmentsForTherapist(therapistId)
    }

    /**
     * Get appointments for aspecific therapist filtered by year & month
     */
    fun getAppointmentsForTherapistById(
        therapistId: String,
        yearMonth: YearMonth?
    ): Flow<List<AppointmentEntity>> {

        return if (yearMonth == null) {
            repository.getAppointmentsForTherapist(therapistId)
        } else {
            val (start, end) = yearMonth.toMillisRange()
            repository.getAppointmentsOnDateWithTherapistId(
                therapistId,
                start,
                end
            )
        }
    }

    /**
     * Get appointments for currently logged in patient
     */
    fun getAppointmentsForPatient(): Flow<List<AppointmentEntity>> {
        val patientId = sessionManager.session.value?.userid
            ?: return flowOf(emptyList())
        return repository.getAppointmentsForPatient(patientId)
    }

    /**
     * Get patients name by ID
     */
    suspend fun getPatientName(patientUserId: String?): String? {
        return repository.getPatientName(patientUserId)
    }

    /**
     * Cancelling appointment
     */
    fun cancelAppointment(appointment: AppointmentEntity) {
        val currentUserId = sessionManager.getUserId()
        val currentRole = sessionManager.getRole()

        if (currentUserId == null || currentRole != UserRole.PATIENT) return
        if (appointment.patientUserId != currentUserId) return

        viewModelScope.launch {
            repository.updateAppointment(
                appointment.copy(
                    isBooked = false,
                    patientUserId = null
                )
            )
        }
    }


}
