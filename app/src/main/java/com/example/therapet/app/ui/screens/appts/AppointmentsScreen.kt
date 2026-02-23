package com.example.therapet.app.ui.screens.appts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.R
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.data.entity.BookingStatus
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.ui.components.fields.appt.AddAppointmentDialog
import com.example.therapet.app.ui.components.fields.appt.AppointmentCell
import com.example.therapet.app.ui.components.fields.appt.EditAppointmentDialog
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Appointments screen UI
 *
 * UI displaying the therapists POV of appointments and managing the interactions
 */

@Composable
fun AppointmentsScreen(
    role: UserRole, // Users role
    appointments: List<AppointmentEntity>, // Appointments list
    onBack: () -> Unit, // navigatte back
    onAddAppointment: (Long, AppointmentType) -> Unit, // Call to add appt
    onUpdateAppointment: (AppointmentEntity) -> Unit, // Call to update an appt
    onDeleteAppointment: (AppointmentEntity) -> Unit, // Call to delete an appt
    modifier: Modifier = Modifier,
    getPatientName: (String?) -> String?, // Retrieving patient name from ID
) {
    var showAddAppointmentDialog by remember { mutableStateOf(false) }
    var selectedAppointment by remember { mutableStateOf<AppointmentEntity?>(null) }

    Scaffold(
        topBar = {
            BasicTopBar(
                text = stringResource(R.string.appointments),
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            if (role == UserRole.THERAPIST) {
                MyBookAppointmentsButton(
                    role = role,
                    onClick = { showAddAppointmentDialog = true }
                )
            }
        }
    ) { innerPadding ->

        /**
         * List of appointments
         */
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(appointments, key = { it.appointmentId }) { appointment ->
                AppointmentCell(
                    appointment = appointment,
                    onClick = { selectedAppointment = appointment },
                    modifier = modifier.testTag("appointment_cell")
                )
            }
        }

        /**
         * Add appointment dialog
         */
        if (showAddAppointmentDialog) {
            AddAppointmentDialog(
                onDismiss = { showAddAppointmentDialog = false },
                onConfirm = { millis, type ->
                    onAddAppointment(millis, type)
                    showAddAppointmentDialog = false
                }
            )
        }

        /**
         * Edit appointment dialog when appointment is selected
         */
        selectedAppointment?.let { appointment ->
            EditAppointmentDialog(
                role = role,
                appointment = appointment,
                onDismiss = { selectedAppointment = null },
                onUpdateTime = { updated ->
                    onUpdateAppointment(updated)
                    selectedAppointment = null
                },
                onDelete = {
                    onDeleteAppointment(it)
                    selectedAppointment = null
                },
                onCancelBooking = {
                    onUpdateAppointment(
                        it.copy(
                            status = BookingStatus.AVAILABLE,
                            patientUserId = null
                        )
                    )
                    selectedAppointment = null
                },
                onApprove = {
                    onUpdateAppointment(
                        it.copy(status = BookingStatus.APPROVED)
                    )
                    selectedAppointment = null
                },
                onReject = {
                    onUpdateAppointment(
                        it.copy(
                            status = BookingStatus.AVAILABLE,
                            patientUserId = null
                        )
                    )
                    selectedAppointment = null
                },
                getPatientName = { id -> id?.let { getPatientName(it) } ?: "" }
            )
        }
    }
}

/**
 * Floating action button for adding a new appointment
 */
@Composable
fun MyBookAppointmentsButton(
    role: UserRole, //Users role
    onClick: () -> Unit, // Click callback
    modifier: Modifier = Modifier
) {
    CustomOutlinedButton(
        onClick = onClick,
        text = "+ Add appointment",
        modifier = modifier
            .testTag("book_appointment_button")
            .height(60.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AppointmentsScreenPreview() {

    val sampleAppointments = listOf(
        AppointmentEntity(
            appointmentId = 1,
            therapistUserId = "therapist_1",
            appointmentDateTime = System.currentTimeMillis() + 86400000,
            appointmentType = AppointmentType.FOLLOW_UP,
            patientUserId = "patient_1",
            status = BookingStatus.AVAILABLE
        ),
        AppointmentEntity(
            appointmentId = 2,
            therapistUserId = "therapist_1",
            appointmentDateTime = System.currentTimeMillis() + 172800000,
            appointmentType = AppointmentType.FOLLOW_UP,
            patientUserId = null,
            status = BookingStatus.AVAILABLE
        )
    )

    TheraPetTheme {
        AppointmentsScreen(
            role = UserRole.THERAPIST,
            appointments = sampleAppointments,
            onBack = {},
            onAddAppointment = { _, _ -> },
            onUpdateAppointment = {},
            onDeleteAppointment = {},
            getPatientName = { id ->
                when (id) {
                    "patient_1" -> "John Smith"
                    else -> "Unknown"
                }
            }
        )
    }
}
