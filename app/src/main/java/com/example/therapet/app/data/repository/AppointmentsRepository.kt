package com.example.therapet.app.data.repository

import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.local.dao.AppointmentDao
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.AppointmentRepositoryContract
import kotlinx.coroutines.flow.Flow

/**
 * Implementing AppointmentRepositoryContract
 *
 * Handles creating, updating, deleting and retrieving appointments
 */
class AppointmentRepository(
    private val appointmentDao: AppointmentDao,
    private val userDao: UserDao
) : AppointmentRepositoryContract {

    /**
     * Creates a new appointment for the therapist
     *
     * @param therapistUserId The ID of the therapist
     * @param dateTimeMillis The appointment date & time in milliseconds
     * @param appointmentType The type of appointment
     * @throws IllegalStateException if the user is not found / is not a therapist
     */
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

    /**
     * Retrieves all appointments for a specific therapist
     *
     * @param therapistUserId the ID of the therapist
     * @return Flow emitting a list of appt objects
     */
    override fun getAppointmentsForTherapist(
        therapistUserId: String
    ): Flow<List<AppointmentEntity>> =
        appointmentDao.getAppointmentsForTherapist(therapistUserId)

    /**
     * Retrieves appointments for a therapist within a date range
     *
     * @param therapistUserId the ID of the therapist
     * @param startMillis Start of the date range in milliseconds
     * @param endMillis End of the date range in milliseconds
     * @return Flow emitting a list of appointment objects
     */
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

    /**
     * Updates an existing appointment in the database
     *
     * @param appointment The appointment to update
     */
    override suspend fun updateAppointment(appointment: AppointmentEntity) {
        appointmentDao.updateAppointment(appointment)
    }

    /**
     * Deletes an appointment from the database
     *
     * @param appointment The appointment to delete
     */
    override suspend fun deleteAppointment(appointment: AppointmentEntity) {
        appointmentDao.deleteAppointment(appointment)
    }

    /**
     * Retrieves all appointments for a specific patient
     *
     * @param patientUserId The ID of the patient
     * @return Flow emitting a list of AppointmentEntity objects
     */
    override fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>> {
        return appointmentDao.getAppointmentsForPatient(patientUserId)
    }

    /**
     * Retrieves the full name of a patient by ID
     *
     * @param patientUserId The ID of the patient
     * @return The patient’s full name / null if its not found
     */
    override suspend fun getPatientName(patientUserId: String?): String? {
        if(patientUserId == null) return null
        val patient = userDao.getUserById(patientUserId)
        return patient?.let { "${it.firstname} ${it.surname}"}
    }
}