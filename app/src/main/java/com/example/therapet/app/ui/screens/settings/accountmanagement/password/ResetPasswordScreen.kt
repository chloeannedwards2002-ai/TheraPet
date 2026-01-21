package com.example.therapet.app.ui.screens.settings.accountmanagement.password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton
import com.example.therapet.app.ui.screens.register.ConfPasswordInput
import com.example.therapet.app.ui.screens.register.PasswordInput


/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Reset password screen UI
 */

@Composable
fun ResetPasswordScreen(
    onResetPassword:(String, String) -> Unit,
    errorMessage: String? = null,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
){
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        
    ){
        innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.reset_password_title),
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(innerPadding)
            .fillMaxSize()
            .testTag("reset_password_screen"),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ){

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = stringResource(R.string.new_password_unique),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = modifier.testTag("new_password_text")
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.enter_new_password),
                modifier = Modifier.fillMaxWidth().testTag("enter_new_password_text"),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium,
            )

            PasswordInput(
                password = password,
                onPasswordChange = { password = it }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.confirm_password),
                modifier = Modifier.fillMaxWidth().testTag("confirm_password_text"),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )

            ConfPasswordInput(
                password = password,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .testTag("reset_password_error_text")
                )
            }

            ResetPasswordButton(
                onClick = {
                    onResetPassword(password, confirmPassword)
                }
            )
        }
    }
}

@Composable
fun ResetPasswordButton(onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.reset_password_title),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .testTag("reset_password_button")
    )
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordPreview() {
    TheraPetTheme {
        ResetPasswordScreen(
            onBack = {},
            errorMessage = null,
            onResetPassword = { _, _ -> }
        )
    }
}
