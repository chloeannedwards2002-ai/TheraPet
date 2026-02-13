package com.example.therapet.repositories

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.repository.contracts.AppointmentRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * @author: Chloe Edwards
 * @date: 02/02/2026
 *
 * A fake appointment repository for tests
 */

class FakeAppointmentRepository : AppointmentRepositoryContract {

    private val appointments =
        MutableStateFlow<List<AppointmentEntity>>(emptyList())

    override suspend fun createAppointment(
        therapistUserId: String,
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    ) {
        val appointment = AppointmentEntity(
            appointmentId = appointments.value.size + 1,
            therapistUserId = therapistUserId,
            appointmentDateTime = dateTimeMillis,
            appointmentType = appointmentType,
            patientUserId = null,
            isBooked = false
        )

        appointments.value = appointments.value + appointment
    }

    override fun getAppointmentsForTherapist(
        therapistUserId: String
    ): Flow<List<AppointmentEntity>> {
        return appointments.map {
            it.filter { appt -> appt.therapistUserId == therapistUserId }
        }
    }

    override suspend fun updateAppointment(updated: AppointmentEntity) {
        appointments.value =
            appointments.value.map {
                if (it.appointmentId == updated.appointmentId) updated else it
            }
    }

    override suspend fun deleteAppointment(appointment: AppointmentEntity) {
        appointments.value =
            appointments.value.filterNot {
                it.appointmentId == appointment.appointmentId
            }
    }

    override fun getAppointmentsOnDateWithTherapistId(
        therapistUserId: String,
        startMillis: Long,
        endMillis: Long
    ): Flow<List<AppointmentEntity>> {
        return appointments.map { list ->
            list.filter { appt ->
                appt.therapistUserId == therapistUserId &&
                        appt.appointmentDateTime in startMillis..endMillis
            }
        }
    }

    override fun getAppointmentsForPatient(
        patientUserId: String
    ): Flow<List<AppointmentEntity>> {
        return appointments.map { list ->
            list.filter { appt ->
                appt.patientUserId == patientUserId
            }
        }
    }
}