package com.example.therapet.app.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.BasicTopBar
import com.example.therapet.app.ui.components.MyOutlinedButton
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.R

@Composable
fun AppointmentsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onBookAppt: () -> Unit
){
    Scaffold(
        floatingActionButton = {
            MyBookAppointmentsButton (
                onClick = onBookAppt,
                modifier = Modifier
            )
        },
    ){
        innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.appointments),
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("appointments_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }
    }
}

@Composable
fun MyBookAppointmentsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    MyOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.book_appointment_nav),
        modifier = modifier
            .testTag("book_appointment_button")
            .height(60.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AppointmentsPreview() {
    TheraPetTheme {
        AppointmentsScreen(
            onBack = {},
            onBookAppt = {}
        )
    }
}