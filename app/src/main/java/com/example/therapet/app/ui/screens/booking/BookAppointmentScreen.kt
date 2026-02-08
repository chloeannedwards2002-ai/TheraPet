package com.example.therapet.app.ui.screens.booking

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Appointment booking screen UI
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
                text = stringResource(R.string.choose_date_and_time),
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
        }
    }
}

@Composable
fun BookButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.book),
        modifier = modifier
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
