package com.example.therapet.app.ui.components.fields.appt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.entity.BookingStatus
import com.example.therapet.app.data.model.UserRole
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView

@Composable
fun EditAppointmentDialog(
    role: UserRole,
    appointment: AppointmentEntity,
    onDismiss: () -> Unit,
    onUpdateTime: (AppointmentEntity) -> Unit,
    onDelete: (AppointmentEntity) -> Unit,
    onCancelBooking: (AppointmentEntity) -> Unit,
    onApprove: (AppointmentEntity) -> Unit = {},
    onReject: (AppointmentEntity) -> Unit = {},
    getPatientName: (String) -> String?,
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
                    text = formatDateTime(appointment.appointmentDateTime),
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = when (appointment.status) {
                        BookingStatus.AVAILABLE -> "Available"
                        BookingStatus.PENDING -> "Pending approval"
                        BookingStatus.APPROVED -> "Confirmed: $patientName"
                        BookingStatus.REJECTED -> "Rejected"
                    },
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(Modifier.height(8.dp))

                if (role == UserRole.THERAPIST) {

                    if (appointment.status == BookingStatus.PENDING) {

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onApprove(appointment) }
                        ) {
                            Text("Approve booking",
                                style = MaterialTheme.typography.labelMedium)
                        }

                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onReject(appointment) }
                        ) {
                            Text("Reject booking",
                                style = MaterialTheme.typography.labelMedium)
                        }

                        Spacer(Modifier.height(8.dp))
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("edit_time_button"),
                        onClick = { showDatePicker = true }
                    ) {
                        Text("Edit time",
                            style = MaterialTheme.typography.labelMedium)
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
                        Text("Delete appointment",
                            style = MaterialTheme.typography.labelMedium)
                    }
                }


                if (role == UserRole.PATIENT) {

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("cancel_appointment_button"),
                        onClick = { showCancelConfirm = true }
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    WheelDateTimePickerView(
                        modifier = Modifier.fillMaxWidth(),
                        showDatePicker = true,
                        height = 200.dp,
                        rowCount = 3,
                        yearsRange = 2024..2030,
                        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
                        onDoneClick = { date ->
                            val millis = date
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
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Cancel appointment", style = MaterialTheme.typography.titleMedium)
                    Text("Are you sure?")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showCancelConfirm = false }) {
                            Text("No")
                        }

                        TextButton(
                            onClick = {
                                showCancelConfirm = false
                                onCancelBooking(appointment)
                                showSuccessMessage = true
                            }
                        ) {
                            Text("Yes")
                        }
                    }
                }
            }
        }
    }

    if (showSuccessMessage) {
        Dialog(onDismissRequest = {
            showSuccessMessage = false
            onDismiss()
        }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Cancelled", style = MaterialTheme.typography.titleMedium)
                    Text("Your appointment has been cancelled.")

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
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}