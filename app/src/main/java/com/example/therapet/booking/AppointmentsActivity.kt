package com.example.therapet.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun AppointmentsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){
    Scaffold(
    ){
        innerPadding ->

        BasicTopBar(
            text = "Appointments",
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

@Preview(showBackground = true)
@Composable
fun AppointmentsPreview() {
    TheraPetTheme {
        AppointmentsScreen(
            onBack = {}
        )
    }
}