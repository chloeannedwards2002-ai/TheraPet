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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.TheraPetTheme

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
            text = "Reset Password",
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
                text = "Your new password must be different from previously used passwords",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Enter new password",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelMedium
            )

            PasswordInput()

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Confirm password",
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
    MyFilledButton(
        onClick = onClick,
        text = "Reset Password",
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