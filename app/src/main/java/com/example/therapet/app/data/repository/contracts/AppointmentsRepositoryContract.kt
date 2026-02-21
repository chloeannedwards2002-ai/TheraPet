package com.example.therapet.app.data.repository.contracts

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 28/01/2026
 *
 * Appointment repository contract (abstraction)
 */

interface AppointmentRepositoryContract {

    /**
     * Returns all appointments for a therapist
     */
    fun getAppointmentsForTherapist(
        therapistUserId: String
    ): Flow<List<AppointmentEntity>>

    /**
     * Creates a new appointment for the therapist
     */
    suspend fun createAppointment(
        therapistUserId: String,
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    )

    /**
     * Returns appts for a therapist with a date range
     */
    fun getAppointmentsOnDateWithTherapistId(
        therapistUserId: String,
        startMillis: Long,
        endMillis: Long
    ): Flow<List<AppointmentEntity>>

    /**
     * Updates an existing appointment
     */
    suspend fun updateAppointment(appointment: AppointmentEntity)

    /**
     * Deletes an appointment
     */
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    /**
     * Returns all appointments for a patient
     */
    fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>>

    /**
     * Retrieves a patient's name using their ID
     */
    suspend fun getPatientName(patientUserId: String?): String?
}
