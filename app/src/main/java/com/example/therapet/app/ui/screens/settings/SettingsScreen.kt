package com.example.therapet.app.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.R
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton
import com.example.therapet.app.ui.components.buttons.general.CustomTonalFilledButton

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Settings screen UI
 */


@Composable
fun SettingsScreen(
    role: UserRole,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onPetSettings: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onHelpSupport: () -> Unit,
    onDeleteAccount: () -> Unit,
    onPermissions: () -> Unit
){
    Scaffold(

    ){
        innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.settings),
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("settings_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Spacer(modifier = Modifier.height(70.dp))

            LanguageButton(onClick = { /* TODO: Navigate to Language settings */})

            NotificationButton(onClick = {/* TODO: Navigate to Notifications settings */})

            BackupButton(onClick = {/* TODO: Navigate to Backup settings */})

            if (role == UserRole.PATIENT) {
                PetSettingsButton(onClick = onPetSettings)
            }

            SecurityButton(onClick = {/* TODO: Navigate to Security settings */})

            PermissionsButton(onClick = onPermissions)

            PrivacyButton(onClick = onPrivacyPolicy)

            HelpSupportButton(onClick = onHelpSupport)
            Spacer(modifier = Modifier.height(20.dp))

            DeleteAccountNavButton(onClick = onDeleteAccount)
        }
    }
}

// Language button
@Composable
fun LanguageButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.language),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("language_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

// Notifs button
@Composable
fun NotificationButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.notifications),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("notification_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

// Backup button
@Composable
fun BackupButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.backup),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("backup_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

//Pet Settings button
@Composable
fun PetSettingsButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.pet_settings),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("pet_settings_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

//Security button
@Composable
fun SecurityButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.security),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("security_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

//Permissions button
@Composable
fun PermissionsButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.permissions),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("permissions_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

// Privacy policy button
@Composable
fun PrivacyButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.privacy_policy),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("privacy_policy_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

@Composable
// Help & support button
fun HelpSupportButton(onClick: () -> Unit){
    CustomOutlinedButton(
        onClick = onClick,
        text = stringResource(R.string.help_and_support),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("help_support_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

// Delete account button
@Composable
fun DeleteAccountNavButton(onClick: () -> Unit){
    CustomTonalFilledButton(
        onClick = onClick,
        text = stringResource(R.string.delete_account),
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("delete_account_button")
            .height(60.dp)
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.errorContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsPatientPreview() {
    TheraPetTheme {
        SettingsScreen(
            onBack = {},
            onPetSettings = {},
            onPrivacyPolicy = {},
            onHelpSupport = {},
            onDeleteAccount = {},
            onPermissions = {},
            role = UserRole.PATIENT
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsTherapistPreview() {
    TheraPetTheme {
        SettingsScreen(
            onBack = {},
            onPetSettings = {},
            onPrivacyPolicy = {},
            onHelpSupport = {},
            onDeleteAccount = {},
            onPermissions = {},
            role = UserRole.THERAPIST
        )
    }
}