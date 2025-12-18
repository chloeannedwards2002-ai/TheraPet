package com.example.therapet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.TheraPetTheme
import com.example.therapet.ui.theme.ToggleField

@Composable
fun PetSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    var remindersEnabled by remember { mutableStateOf(false) }
    var petSoundsEnabled by remember { mutableStateOf(false) }
    var hibernationEnabled by remember { mutableStateOf(false) }

    Scaffold(

    ){
        innerPadding ->

        BasicTopBar(
            text = "Pet Settings",
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("pet_settings_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(100.dp))

            ToggleField(
                label = "Appointment Reminders",
                checked = remindersEnabled,
                onCheckedChange = { remindersEnabled = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "By turning off these notifications, your pet will no longer remind you of upcoming appointments",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(10.dp))

            ToggleField(
                label = "PetSounds",
                checked = petSoundsEnabled,
                onCheckedChange = { remindersEnabled = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "By turning off this setting, your pet will no longer make sounds",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(10.dp))

            ToggleField(
                label = "Hibernation",
                checked = hibernationEnabled,
                onCheckedChange = { remindersEnabled = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "This will put your pet in 'hibernation' mode, you will no longer have to care for it until your turn it back on",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetSettingsPreview() {
    TheraPetTheme {
        PetSettingsScreen(
            onBack = {}
        )
    }
}