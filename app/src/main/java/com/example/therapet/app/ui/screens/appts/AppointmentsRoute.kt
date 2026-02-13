package com.example.therapet.app.ui.screens.appts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
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

    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = ViewModelFactory.AppointmentViewModelFactory(
            context,
            sessionManager
        )
    )

    val UserViewModel: UserViewModel = viewModel(
        factory = ViewModelFactory.UserViewModelFactory(context, sessionManager)
    )

    val appointments by appointmentViewModel
        .getAppointmentsForTherapist()
        .collectAsState(initial = emptyList())


    AppointmentsScreen(
        role = role,
        appointments = appointments,
        onBack = onBack,
        onAddAppointment = { millis, type ->
            appointmentViewModel.addAppointment(millis, type)
        },
        onUpdateAppointment = { updated ->
            appointmentViewModel.updateAppointment(updated)
        },
        onDeleteAppointment = { appointment ->
            appointmentViewModel.deleteAppointment(appointment)
        }
    )
}