package com.example.therapet.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.therapet.app.data.entity.AppointmentEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author: Chloe Edwards
 * @date: 27/01/2026
 *
 * Appointment data access object
 */

@Dao
interface AppointmentDao {

    /**
     * Inserts a new appointment record into the database
     *
     * @param appointment the AppointmentEntity being saved
     */
    @Insert
    suspend fun insertAppointment(appointment: AppointmentEntity)

    /**
     * Gets all the appointments for a specific therapist
     *
     * @param therapistId The unique ID for the therapist
     * @return Flow emitting the list of AppointmentEntity objects
     */
    @Query("""
        SELECT * FROM appointments
        WHERE therapistUserId = :therapistId
        ORDER BY appointmentDateTime ASC
    """)
    fun getAppointmentsForTherapist(
        therapistId: String
    ): Flow<List<AppointmentEntity>>


    /**
     * Retrieves the appointments for a specific therapist in a given time range
     *
     * Filter is applied on the patient appointment screen step 3
     *
     * @param therapistId The unique ID for the therapist
     * @param startMillis Start time of the range in milli seconds
     * @param endMillis End time of the range in milli seconds
     * @return Flow emitting a list of matching AppointmentEnttiy objects
     */
    @Query("""
    SELECT * FROM appointments
    WHERE therapistUserId = :therapistId
    AND appointmentDateTime BETWEEN :startMillis AND :endMillis
    ORDER BY appointmentDateTime ASC
""")
    fun getAppointmentsOnDateWithTherapistId(
        therapistId: String,
        startMillis: Long,
        endMillis: Long
    ): Flow<List<AppointmentEntity>>

    /**
     * Updates an existing appointment in the database
     *
     * @param appointment The updated entity
     */
    @Update
    suspend fun updateAppointment(appointment: AppointmentEntity)

    /**
     * Deletes an appointment from the database
     *
     * @param appointment The appointment entity
     */
    @Delete
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    /**
     * Retrieves all the appointments associated with a specific patient
     *
     * @param patientUserId The unique ID of the patient
     * @return Flow emitting a list of appointment objects
     */
    @Query("SELECT * FROM appointments WHERE patientUserId = :patientUserId")
    fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>>

}