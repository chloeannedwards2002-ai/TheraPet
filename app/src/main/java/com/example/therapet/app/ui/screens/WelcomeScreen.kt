package com.example.therapet.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Welcome screen UI
 */

//Composing the full screen
@Composable
fun WelcomeScreen(
    onRegisterNav: () -> Unit,
    onLoginNav: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .testTag("welcome_screen"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Welcome to ${stringResource(R.string.app_name)}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        ChooseRegisterButton(
            onClick = onRegisterNav
        )

        ChooseLoginButton(
            onClick = onLoginNav
        )
    }
}

/*
    These are the composables for the register and login buttons using custom filled button fragments
 */

//Register button
@Composable
fun ChooseRegisterButton(onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.register),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("choose_register_button") // For Jetpack UI tests
    )
}

//Login button
@Composable
fun ChooseLoginButton(onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.login),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("choose_login_button") // For Jetpack UI tests
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    TheraPetTheme {
        WelcomeScreen(
            onRegisterNav = {},
            onLoginNav = {}
        )
    }
}