package com.example.therapet.app.ui.screens.settings.accountmanagement

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomElevatedButton
import com.example.therapet.app.ui.components.fields.input.CustomOutlinedTextField
import kotlinx.coroutines.launch


/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Delete account screen UI
 */

@Composable
fun DeleteAccountScreen(
    onBack: () -> Unit,
    onContinue: () -> Unit,
    validatePassword: suspend (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold { innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.delete_account),
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("delete_account_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = stringResource(R.string.delete_account_warning),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.testTag("delete_info_text")
            )

            Spacer(modifier = Modifier.height(24.dp))

            PasswordTextField(
                password = password,
                onPasswordChange = {
                    password = it
                    showError = false
                }
            )

            if (showError) {
                Text(
                    text = stringResource(R.string.passwords_do_not_match),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .testTag("password_error_text")
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            DeleteAccountContinueButton {
                scope.launch {
                    if (validatePassword(password)) {
                        onContinue()
                    } else {
                        showError = true
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomOutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = stringResource(R.string.enter_password),
        label = stringResource(R.string.password),
        modifier = modifier.testTag("password_confirm_input")
    )
}

@Composable
fun DeleteAccountContinueButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    CustomElevatedButton(
        modifier = modifier
            .testTag("delete_account_confirm_button"),
        text = stringResource(R.string.continue_button),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountPreview() {
    TheraPetTheme {
        DeleteAccountScreen(
            onBack = {},
            onContinue = {},
            validatePassword = { _ -> true }
        )
    }
}