package com.example.therapet.app.ui.components.fields.appt

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * @Author: Chloe Edwards
 * @Date: 03/02/2026
 *
 * A template appointment cell that sotres information of all appointments in the database
 */

// Formatting the date and time
fun formatDateTime(millis: Long): String {
    val dateTime = Instant
        .fromEpochMilliseconds(millis)
        .toLocalDateTime(TimeZone.currentSystemDefault())

    return "${dateTime.date} at ${dateTime.hour}:${dateTime.minute.toString().padStart(2, '0')}"
}

@Composable
fun appointmentCardColours(isBooked: Boolean): CardColors {
    return if (isBooked) {
        CardDefaults.cardColors()
    } else {
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    }
}

@Composable
fun AppointmentCell(
    appointment: AppointmentEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = appointmentCardColours(appointment.isBooked)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = appointment.appointmentType.displayName(),
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = formatDateTime(appointment.appointmentDateTime),
                style = MaterialTheme.typography.labelMedium
            )

            if (appointment.isBooked) {
                Text(
                    text = "Booked",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                Text(
                    text = "Available",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF2E7D32)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentCellAvailablePreview() {
    TheraPetTheme {
        AppointmentCell(
            appointment = AppointmentEntity(
                appointmentId = 1,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_700_000_000_000L,
                appointmentType = AppointmentType.CONSULTATION,
                patientUserId = null,
                isBooked = false
            ),
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppointmentCellBookedPreview() {
    TheraPetTheme {
        AppointmentCell(
            appointment = AppointmentEntity(
                appointmentId = 2,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_700_100_000_000L,
                appointmentType = AppointmentType.SESSION,
                patientUserId = "123123123123",
                isBooked = true
            ),
            onClick = {}
        )
    }
}