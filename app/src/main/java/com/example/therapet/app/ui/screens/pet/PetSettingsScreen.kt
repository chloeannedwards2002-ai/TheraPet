package com.example.therapet.app.ui.screens.pet

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
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.app.ui.components.buttons.toggle.ToggleButton
import androidx.compose.ui.res.stringResource
import com.example.therapet.R

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Pet settings screen UI
 */

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
            text = stringResource(R.string.pet_settings),
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

            ToggleButton(
                label = stringResource(R.string.appointment_reminders),
                checked = remindersEnabled,
                onCheckedChange = { remindersEnabled = it },
                modifier = Modifier.testTag("appointment_reminders_toggle")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.appt_reminders_warning),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.testTag("turn_off_notifs_text")
            )

            Spacer(modifier = Modifier.height(10.dp))

            ToggleButton(
                label = stringResource(R.string.pet_sounds),
                checked = petSoundsEnabled,
                onCheckedChange = { remindersEnabled = it },
                modifier = Modifier.testTag("pet_sounds_toggle")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.pet_sounds_warning),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.testTag("toggle_pet_sounds_text")
            )

            Spacer(modifier = Modifier.height(10.dp))

            ToggleButton(
                label = stringResource(R.string.hibernation),
                checked = hibernationEnabled,
                onCheckedChange = { remindersEnabled = it },
                modifier = Modifier.testTag("hibernation_toggle")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.hibernation_warning),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.testTag("toggle_hibernation_text")
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