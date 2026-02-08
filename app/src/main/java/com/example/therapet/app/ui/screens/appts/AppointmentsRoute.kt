package com.example.therapet.app.ui.screens.appts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory

/**
 * @author: Chloe Edwards
 * @date: 23/01/2026
 *
 * Appointments screen route
 */

@Composable
fun AppointmentsRoute(
    role: UserRole,
    sessionManager: SessionManager,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val viewModel: AppointmentViewModel = viewModel(
        factory = ViewModelFactory.AppointmentViewModelFactory(
            context,
            sessionManager
        )
    )

    val appointments by viewModel
        .getAppointmentsForTherapist()
        .collectAsState(initial = emptyList())

    AppointmentsScreen(
        role = role,
        appointments = appointments,
        onBack = onBack,
        onAddAppointment = { millis, type ->
            viewModel.addAppointment(millis, type)
        },
        onUpdateAppointment = { updated ->
            viewModel.updateAppointment(updated)
        },
        onDeleteAppointment = { appointment ->
            viewModel.deleteAppointment(appointment)
        }
    )
}