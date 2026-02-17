package com.example.therapet.app.data.repository.contracts

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 28/01/2026
 *
 * Pet repo contract (abstraction)
 */

interface AppointmentRepositoryContract {

    fun getAppointmentsForTherapist(
        therapistUserId: String
    ): Flow<List<AppointmentEntity>>

    suspend fun createAppointment(
        therapistUserId: String,
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    )

    fun getAppointmentsOnDateWithTherapistId(
        therapistUserId: String,
        startMillis: Long,
        endMillis: Long
    ): Flow<List<AppointmentEntity>>

    suspend fun updateAppointment(appointment: AppointmentEntity)

    suspend fun deleteAppointment(appointment: AppointmentEntity)

    fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>>

    suspend fun getPatientName(patientUserId: String?): String?
}
