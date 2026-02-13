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

    @Insert
    suspend fun insertAppointment(appointment: AppointmentEntity)


    //Used for therapist appointments screen
    @Query("""
        SELECT * FROM appointments
        WHERE therapistUserId = :therapistId
        ORDER BY appointmentDateTime ASC
    """)
    fun getAppointmentsForTherapist(
        therapistId: String
    ): Flow<List<AppointmentEntity>>

    // Used for patient appointments screen
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

    @Update
    suspend fun updateAppointment(appointment: AppointmentEntity)

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    @Query("SELECT * FROM appointments WHERE patientUserId = :patientUserId")
    fun getAppointmentsForPatient(patientUserId: String): Flow<List<AppointmentEntity>>

}