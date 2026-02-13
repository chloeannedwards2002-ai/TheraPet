package com.example.therapet.app.ui.screens.booking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory
import java.time.YearMonth

/**
 * @author: Chloe Edwards
 * @date: 08/02/2026
 *
 * Book Appointment route
 */

@Composable
fun PatientAppointmentsRoute(
    sessionManager: SessionManager,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    var selectedTherapistId by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedYear by rememberSaveable { mutableStateOf<Int?>(null) }
    var selectedMonth by rememberSaveable { mutableStateOf<Int?>(null) }

    val selectedYearMonth =
        if (selectedYear != null && selectedMonth != null) {
            YearMonth.of(selectedYear!!, selectedMonth!!)
        } else null

    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelFactory.UserViewModelFactory(
            context,
            sessionManager
        )
    )

    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = ViewModelFactory.AppointmentViewModelFactory(
            context,
            sessionManager
        )
    )

    var step by rememberSaveable {
        mutableStateOf(BookingStep.PATIENT_APPOINTMENTS)
    }


    LaunchedEffect(Unit) {
        userViewModel.loadTherapists()
    }

    val therapists by userViewModel.therapists.collectAsState()

    val appointments by selectedTherapistId?.let { therapistId ->
        appointmentViewModel
            .getAppointmentsForTherapistById(
                therapistId,
                selectedYearMonth
            )
            .collectAsState(initial = emptyList())
    } ?: remember { mutableStateOf(emptyList()) }

    val patientAppointments by appointmentViewModel
        .getAppointmentsForPatient()
        .collectAsState(initial = emptyList())


    PatientAppointmentsScreen(
        step = step,
        therapists = therapists,
        selectedTherapistId = selectedTherapistId,
        selectedYearMonth = selectedYearMonth,
        appointments = appointments,
        onTherapistSelected = {
            selectedTherapistId = it.userid
            step = BookingStep.BOOK_APPOINTMENT
        },
        onAppointmentClick = { appointment ->
            appointmentViewModel.bookAppointment(appointment)
        },
        onMonthSelected = { yearMonth ->
            selectedYear = yearMonth.year
            selectedMonth = yearMonth.monthValue
        },
        patientAppointments = patientAppointments,
        onBack = {
            if (step == BookingStep.BOOK_APPOINTMENT) {
                step = BookingStep.CHOOSE_THERAPIST
            } else {
                onBack()
            }
        },
        onBook = {}
    )
}