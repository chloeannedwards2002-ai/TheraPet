package com.example.therapet.app.ui.screens.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.R
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton
import com.example.therapet.app.ui.components.fields.account.MinimizedAccountCell
import com.example.therapet.app.ui.components.fields.appt.AppointmentCell
import com.example.therapet.app.ui.components.fields.appt.EditAppointmentDialog
import com.example.therapet.app.ui.components.fields.appt.MonthPicker
import java.time.YearMonth

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * updated: 08/02/2026
 *
 * Choose therapist & Appointment booking screen UI
 */

private val therapistsForPreview = listOf(
    AccountUIModel(
        userid = "12345678910defgt",
        fullName = "Bob Bobbington",
        role = UserRole.PATIENT
    ),
    AccountUIModel(
        userid = "Ghtu745S6gTdHw24",
        fullName = "Jane Doe",
        role = UserRole.PATIENT
    )
)

@Composable
fun PatientAppointmentsScreen(
    step: BookingStep,
    therapists: List<AccountUIModel>,
    selectedTherapistId: String?,
    selectedYearMonth: YearMonth?,
    appointments: List<AppointmentEntity>,
    patientAppointments: List<AppointmentEntity> = emptyList(),
    onTherapistSelected: (AccountUIModel) -> Unit,
    onMonthSelected: (YearMonth) -> Unit,
    onAppointmentClick: (AppointmentEntity) -> Unit,
    onBack: () -> Unit,
    onBook: () -> Unit
) {
    var showMonthPicker by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(YearMonth.now()) }

    var currentStep by remember { mutableStateOf(BookingStep.PATIENT_APPOINTMENTS) }

    var selectedAppointment by remember { mutableStateOf<AppointmentEntity?>(null) }

    Scaffold(
        topBar = {
            BasicTopBar(
                text = when (step) {
                    BookingStep.PATIENT_APPOINTMENTS -> "Appointments"
                    BookingStep.CHOOSE_THERAPIST ->
                        stringResource(R.string.choose_therapist)
                    BookingStep.BOOK_APPOINTMENT ->
                        stringResource(R.string.book_appointment)
                },
                onBackClick = onBack
            )
        },

        floatingActionButton = {
            BookButton(
                onClick = onBook,)
        }
    ) { innerPadding ->

        MonthPicker(
            visible = showMonthPicker,
            currentMonth = selectedYearMonth?.monthValue?.minus(1)
                ?: (YearMonth.now().monthValue - 1),
            currentYear = selectedYearMonth?.year
                ?: YearMonth.now().year,
            confirmButtonClicked = { month, year ->
                onMonthSelected(YearMonth.of(year, month))
                showMonthPicker = false
            },
            cancelClicked = { showMonthPicker = false }
        )

        when (step) {
            BookingStep.PATIENT_APPOINTMENTS -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(patientAppointments, key = { it.appointmentId }) { appointment ->
                        AppointmentCell(
                            appointment = appointment,
                            onClick = { selectedAppointment = appointment }
                        )
                    }
                }

                selectedAppointment?.let { appointment ->
                    EditAppointmentDialog(
                        role = UserRole.PATIENT,
                        appointment = appointment,
                        onDismiss = { selectedAppointment = null },
                        onUpdateTime = {},
                        onDelete = {},
                        onCancelBooking = { onAppointmentClick(it) },
                        getPatientName = { "" }
                    )
                }
            }

            BookingStep.CHOOSE_THERAPIST -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(therapists) { therapist ->
                        MinimizedAccountCell(
                            account = therapist,
                            modifier = Modifier.fillMaxWidth(0.92f),
                            onClick = { onTherapistSelected(therapist) }
                        )
                    }
                }
            }

            BookingStep.BOOK_APPOINTMENT -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = { showMonthPicker = true }) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter"
                            )
                        }
                    }
                    if (appointments.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No appointments available",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(
                                items = appointments,
                                key = { it.appointmentId }
                            ) { appointment ->

                                AppointmentCell(
                                    appointment = appointment,
                                    onClick = {
                                        if (!appointment.isBooked) {
                                            onAppointmentClick(appointment)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = "Book Appointment",
        modifier = modifier
            .fillMaxWidth(0.5F)
            .testTag("book_button"),
    )
}

/* BROKEN PREVIEWS

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BookAppointmentChooseTherapistPreview() {
    TheraPetTheme {
        BookAppointmentScreen(
            step = BookingStep.CHOOSE_THERAPIST,
            therapists = therapistsForPreview,
            selectedTherapistId = null,
            onTherapistSelected = {},
            onBack = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BookAppointmentBookingStepPreview() {
    TheraPetTheme {
        BookAppointmentScreen(
            step = BookingStep.BOOK_APPOINTMENT,
            therapists = emptyList(),
            selectedTherapistId = "12345678910defgt",
            onTherapistSelected = {},
            onBack = {}
        )
    }
}
*/