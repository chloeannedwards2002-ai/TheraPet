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
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.ui.components.fields.appt.AddAppointmentDialog
import com.example.therapet.app.ui.components.fields.appt.AppointmentCell
import com.example.therapet.app.ui.components.fields.appt.EditAppointmentDialog


/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Appointments screen UI
 */


@Composable
fun AppointmentsScreen(
    role: UserRole,
    appointments: List<AppointmentEntity>,
    onBack: () -> Unit,
    onAddAppointment: (Long, AppointmentType) -> Unit,
    onUpdateAppointment: (AppointmentEntity) -> Unit,
    onDeleteAppointment: (AppointmentEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddAppointmentDialog by remember { mutableStateOf(false) }
    var selectedAppointment by remember {
        mutableStateOf<AppointmentEntity?>(null)
    }

    Scaffold(
        topBar = {
            BasicTopBar(
                text = stringResource(R.string.appointments),
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            MyBookAppointmentsButton(
                role = role,
                onClick = { showAddAppointmentDialog = true }
            )
        }
    ) { innerPadding ->

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

        if (showAddAppointmentDialog) {
            AddAppointmentDialog(
                onDismiss = { showAddAppointmentDialog = false },
                onConfirm = { millis, type ->
                    onAddAppointment(millis, type)
                    showAddAppointmentDialog = false
                }
            )
        }

        selectedAppointment?.let { appointment ->
            EditAppointmentDialog(
                appointment = appointment,
                onDismiss = { selectedAppointment = null },
                onUpdateTime = { updated ->
                    onUpdateAppointment(updated)
                    selectedAppointment = null
                },
                onDelete = {
                    onDeleteAppointment(it)
                    selectedAppointment = null
                }
            )
        }

    }
}
@Composable
fun MyBookAppointmentsButton(
    role: UserRole,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val text = when (role) {
        UserRole.PATIENT -> "+ Book appointment"
        UserRole.THERAPIST -> "+ Add appointment"
    }

    CustomOutlinedButton(
        onClick = onClick,
        text = text,
        modifier = modifier
            .testTag("book_appointment_button")
            .height(60.dp)
    )
}
