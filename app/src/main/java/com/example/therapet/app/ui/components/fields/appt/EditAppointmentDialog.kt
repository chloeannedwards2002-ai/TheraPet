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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.therapet.app.data.model.UserRole
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
    role: UserRole,
    appointment: AppointmentEntity,
    onDismiss: () -> Unit,
    onUpdateTime: (AppointmentEntity) -> Unit,
    onDelete: (AppointmentEntity) -> Unit,
    onCancelBooking: (AppointmentEntity) -> Unit,
    getPatientName: (String) -> String?,

    //For previewing
    previewShowCancelConfirm: Boolean = false,
    previewShowSuccess: Boolean = false
) {
    var showDatePicker by remember { mutableStateOf(false) }

    var selectedDateTime by remember {
        mutableStateOf(
            Instant
                .fromEpochMilliseconds(appointment.appointmentDateTime)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }
    val patientName = appointment.patientUserId?.let { getPatientName(it) } ?: ""

    var showCancelConfirm by remember { mutableStateOf(previewShowCancelConfirm) }
    var showSuccessMessage by remember { mutableStateOf(previewShowSuccess) }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
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
                    text = if (appointment.isBooked)
                        "Booked by: $patientName"
                    else
                        "Available",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (appointment.isBooked)
                        MaterialTheme.colorScheme.error
                    else
                        Color(0xFF2E7D32)
                )

                Spacer(Modifier.height(8.dp))

                if (role == UserRole.THERAPIST) {

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("edit_time_button"),
                        onClick = { showDatePicker = true }
                    ) {
                        Text(
                            "Edit time",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("delete_appointment_button"),
                        onClick = { onDelete(appointment) },
                        colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer
                            )
                    ) {
                        Text(
                            "Delete appointment",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                } else if (role == UserRole.PATIENT) {

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("cancel_appointment_button"),
                        onClick = { showCancelConfirm = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text("Cancel appointment",
                            style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        Dialog(onDismissRequest = { showDatePicker = false }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
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

    if (showCancelConfirm) {
        Dialog(onDismissRequest = { showCancelConfirm = false }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        "Cancel appointment",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        "Are you sure?",
                        style = MaterialTheme.typography.labelMedium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { showCancelConfirm = false }
                        ) {
                            Text(
                                "No",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                        TextButton(
                            onClick = {
                                showCancelConfirm = false
                                onCancelBooking(appointment)
                                showSuccessMessage = true
                            }
                        ) {
                            Text(
                                "Yes",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }

    if (showSuccessMessage) {
        Dialog(
            onDismissRequest = {
                showSuccessMessage = false
                onDismiss()
            }
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        "Cancelled",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        "Your appointment has been cancelled.",
                        style = MaterialTheme.typography.labelMedium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                showSuccessMessage = false
                                onDismiss()
                            }
                        ) {
                            Text(
                                "OK",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditAppointmentTherapistDialogPreview() {
    TheraPetTheme {
        EditAppointmentDialog(
            appointment = AppointmentEntity(
                appointmentId = 3,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_700_000_000_000L,
                appointmentType = AppointmentType.FOLLOW_UP,
                patientUserId = "123123123123",
                isBooked = true
            ),
            onDismiss = {},
            onUpdateTime = {},
            onDelete = {},
            getPatientName = { "Jane Doe" },
            onCancelBooking = {},
            role = UserRole.THERAPIST
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditAppointmentPatientDialogPreview() {
    TheraPetTheme {
        EditAppointmentDialog(
            appointment = AppointmentEntity(
                appointmentId = 3,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_800_000_000_000L,
                appointmentType = AppointmentType.CONSULTATION,
                patientUserId = "123123123123",
                isBooked = true
            ),
            onDismiss = {},
            onUpdateTime = {},
            onDelete = {},
            getPatientName = { "John Smith" },
            onCancelBooking = {},
            role = UserRole.THERAPIST
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditAppointmentCancelConfirmPreview() {
    TheraPetTheme {
        EditAppointmentDialog(
            role = UserRole.PATIENT,
            appointment = AppointmentEntity(
                appointmentId = 1,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_800_000_000_000L,
                appointmentType = AppointmentType.CONSULTATION,
                patientUserId = "123123123123",
                isBooked = true
            ),
            onDismiss = {},
            onUpdateTime = {},
            onDelete = {},
            onCancelBooking = {},
            getPatientName = { "John Smith" },
            previewShowCancelConfirm = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditAppointmentSuccessPreview() {
    TheraPetTheme {
        EditAppointmentDialog(
            role = UserRole.PATIENT,
            appointment = AppointmentEntity(
                appointmentId = 2,
                therapistUserId = "1231231231231234",
                appointmentDateTime = 1_800_000_000_000L,
                appointmentType = AppointmentType.FOLLOW_UP,
                patientUserId = "12312312312",
                isBooked = true
            ),
            onDismiss = {},
            onUpdateTime = {},
            onDelete = {},
            onCancelBooking = {},
            getPatientName = { "Jane Doe" },
            previewShowSuccess = true
        )
    }
}