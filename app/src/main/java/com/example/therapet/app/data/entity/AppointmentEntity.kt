package com.example.therapet.app.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.therapet.app.data.model.AppointmentType

/**
 * @author: Chloe Edwards
 * @date: 26/01/2026
 *
 * The appointment database table
 */

@Entity(
    tableName = "appointments",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userid"],
            childColumns = ["therapistUserId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userid"],
            childColumns = ["patientUserId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("therapistUserId"),
        Index("patientUserId")
    ]
)
data class AppointmentEntity(

    @PrimaryKey(autoGenerate = true)
    val appointmentId: Int = 0,
    val therapistUserId: String,
    val appointmentDateTime: Long,
    val appointmentType: AppointmentType,
    val patientUserId: String? = null,
    val isBooked: Boolean = false
)
