package com.example.therapet.app.data.repository

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.local.dao.AppointmentDao
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.AppointmentRepositoryContract
import kotlinx.coroutines.flow.Flow

class AppointmentRepository(
    private val appointmentDao: AppointmentDao,
    private val userDao: UserDao
) : AppointmentRepositoryContract {

    override suspend fun createAppointment(
        therapistUserId: String,
        dateTimeMillis: Long,
        appointmentType: AppointmentType
    ) {
        val therapist = userDao.getUserById(therapistUserId)
            ?: throw IllegalStateException("User not found")

        if (therapist.role != UserRole.THERAPIST) {
            throw IllegalStateException("This user is not a therapist")
        }

        val appointment = AppointmentEntity(
            therapistUserId = therapist.userid,
            appointmentDateTime = dateTimeMillis,
            appointmentType = appointmentType,
            patientUserId = null,
            isBooked = false
        )

        appointmentDao.insertAppointment(appointment)
    }

    override fun getAppointmentsForTherapist(
        therapistUserId: String
    ): Flow<List<AppointmentEntity>> =
        appointmentDao.getAppointmentsForTherapist(therapistUserId)

    override fun getAppointmentsOnDateWithTherapistId(
        therapistUserId: String,
        startMillis: Long,
        endMillis: Long
    ): Flow<List<AppointmentEntity>> =
        appointmentDao.getAppointmentsOnDateWithTherapistId(
            therapistUserId,
            startMillis,
            endMillis
        )

    override suspend fun updateAppointment(appointment: AppointmentEntity) {
        appointmentDao.updateAppointment(appointment)
    }

    override suspend fun deleteAppointment(appointment: AppointmentEntity) {
        appointmentDao.deleteAppointment(appointment)
    }

    override fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>> {
        return appointmentDao.getAppointmentsForPatient(patientUserId)
    }
}