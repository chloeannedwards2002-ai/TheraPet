package com.example.therapet.app.ui.components.fields.appt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView

/**
 * @Author: Chloe Edwards
 * @Date: 08/02/2026
 *
 * Editing / deleting appointments dialog
 */

@Composable
fun EditAppointmentDialog(
    appointment: AppointmentEntity,
    onDismiss: () -> Unit,
    onUpdateTime: (AppointmentEntity) -> Unit,
    onDelete: (AppointmentEntity) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    var selectedDateTime by remember {
        mutableStateOf(
            Instant
                .fromEpochMilliseconds(appointment.appointmentDateTime)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }

    //Not ideal - repeating data that doesnt need to be repeated - will be updated eventually
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Appointment",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Text(
                    text = appointment.appointmentType.displayName(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatDateTime(
                        Instant.fromEpochMilliseconds(appointment.appointmentDateTime)
                            .toEpochMilliseconds()
                    ),
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = if (appointment.isBooked) "Booked" else "Available",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (appointment.isBooked)
                        MaterialTheme.colorScheme.error
                    else
                        Color(0xFF2E7D32)
                )

                Spacer(Modifier.height(8.dp))


                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("edit_time_button"),
                    onClick = { showDatePicker = true }
                ) {
                    Text("Edit time",
                        style = MaterialTheme.typography.labelMedium)
                }

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("delete_appointment_button"),
                    onClick = { onDelete(appointment) }
                ) {
                    Text("Delete appointment",
                        style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }


    if (showDatePicker) {
        Dialog(onDismissRequest = { showDatePicker = false }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    WheelDateTimePickerView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("date_time_picker"),
                        showDatePicker = true,
                        height = 200.dp,
                        rowCount = 3,
                        yearsRange = 2024..2030,
                        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
                        onDoneClick = { it ->
                            selectedDateTime = it

                            val millis = it
                                .toInstant(TimeZone.currentSystemDefault())
                                .toEpochMilliseconds()

                            onUpdateTime(
                                appointment.copy(
                                    appointmentDateTime = millis
                                )
                            )

                            showDatePicker = false
                            onDismiss()
                        },
                        onDismiss = { showDatePicker = false }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditAppointmentDialogPreview() {
    TheraPetTheme {
        EditAppointmentDialog(
            appointment = AppointmentEntity(
                appointmentId = 3,
                therapistUserId = "therapist-123",
                appointmentDateTime = 1_700_000_000_000L,
                appointmentType = AppointmentType.FOLLOW_UP,
                patientUserId = null,
                isBooked = false
            ),
            onDismiss = {},
            onUpdateTime = {},
            onDelete = {}
        )
    }
}