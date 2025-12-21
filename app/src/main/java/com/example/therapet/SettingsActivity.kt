package com.example.therapet

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
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyFilledTonalButton
import com.example.therapet.ui.theme.MyOutlinedButton
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onPetSettings: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onHelpSupport: () -> Unit,
    onDeleteAccount: () -> Unit
){
    Scaffold(

    ){
        innerPadding ->

        BasicTopBar(
            text = "Settings",
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

            PetSettingsButton(onClick = onPetSettings)

            SecurityButton(onClick = {/* TODO: Navigate to Security settings */})

            PermissionsButton(onClick = {/* TODO: Navigate to Permissions settings */})

            PrivacyButton(onClick = onPrivacyPolicy)

            HelpSupportButton(onClick = onHelpSupport)
            Spacer(modifier = Modifier.height(20.dp))

            DeleteAccountButton(onClick = onDeleteAccount)
        }
    }
}

// Language button
@Composable
fun LanguageButton(onClick: () -> Unit){
    MyOutlinedButton(
        onClick = onClick,
        text = "Language",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Notifications",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Backup",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Pet Settings",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Security",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Permissions",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Privacy policy",
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
    MyOutlinedButton(
        onClick = onClick,
        text = "Help & Support",
        modifier = Modifier
            .padding(top = 20.dp)
            .testTag("help_support_button")
            .height(60.dp)
            .fillMaxWidth()
    )
}

// Delete account button
@Composable
fun DeleteAccountButton(onClick: () -> Unit){
    MyFilledTonalButton(
        onClick = onClick,
        text = "Delete Account",
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
fun SettingsPreview() {
    TheraPetTheme {
        SettingsScreen(
            onBack = {},
            onPetSettings = {},
            onPrivacyPolicy = {},
            onHelpSupport = {},
            onDeleteAccount = {}
        )
    }
}