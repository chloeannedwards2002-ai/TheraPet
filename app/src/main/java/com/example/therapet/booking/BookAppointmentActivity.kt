package com.example.therapet.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.ui.calendar.CustomCalendar
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.TheraPetTheme

/*
    This is the appointment booking screen where the patient can book an appointment. It will also be state controlled later on
    so the therapist can input available appointments
 */


@Composable
fun BookAppointmentScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onBook: () -> Unit
){
    var selectedDate by remember { mutableStateOf("No date selected") }

    Scaffold(
        topBar = {
            BasicTopBar(
                text = "Choose date and time",
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            BookButton(onClick = onBook)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("book_appointment_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            CustomCalendar(
                onDateSelected = { date ->
                    // TODO: handle selected date
                }
            )
        }
    }
}

@Composable
fun BookButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
        text = "Book",
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .testTag("book_button")
    )
}

@Preview
@Composable
fun BookAppointmentPreview() {
    TheraPetTheme {
        BookAppointmentScreen(
            onBack = {},
            onBook = {}
        )
    }
}
