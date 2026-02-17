package com.example.therapet.app.ui.screens.appts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.WatchlistRepository
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

    val watchlistRepository = WatchlistRepository(
        watchlistDao = AppDatabase.getDatabase(context).watchlistDao(),
        userDao = AppDatabase.getDatabase(context).userDao()
    )

    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = ViewModelFactory.AppointmentViewModelFactory(
            context = context,
            sessionManager = sessionManager,
            watchlistRepository = watchlistRepository
        )
    )

    val appointments by appointmentViewModel
        .getAppointmentsForTherapist()
        .collectAsState(initial = emptyList())

    val patientNames = remember { mutableStateOf<Map<String, String?>>(emptyMap()) }

    LaunchedEffect(appointments) {
        val map = mutableMapOf<String, String?>()
        appointments.forEach { appointment ->
            val patientUserId = appointment.patientUserId
            if (patientUserId != null) {
                map[patientUserId] = appointmentViewModel.getPatientName(patientUserId)
            }
        }
        patientNames.value = map
    }

    AppointmentsScreen(
        role = role,
        appointments = appointments,
        onBack = onBack,
        onAddAppointment = { millis, type -> appointmentViewModel.addAppointment(millis, type) },
        onUpdateAppointment = { updated -> appointmentViewModel.updateAppointment(updated) },
        onDeleteAppointment = { appointment -> appointmentViewModel.deleteAppointment(appointment) },
        getPatientName = { userId -> patientNames.value[userId] }
    )
}