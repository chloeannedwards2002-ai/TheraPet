package com.example.therapet.app.auth

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

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Reset password screen UI
 */

@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onResetPassword: () -> Unit
){
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
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.enter_new_password),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )

            PasswordInput()

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.confirm_password),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )

            PasswordInput()

            Spacer(modifier = Modifier.height(20.dp))

            ResetPasswordButton(onClick = onResetPassword)
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
            onResetPassword = {}
        )
    }
}