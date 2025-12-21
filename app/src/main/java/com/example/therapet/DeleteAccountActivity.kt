package com.example.therapet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyElevatedButton
import com.example.therapet.ui.theme.MyOutlinedTextField
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun DeleteAccountScreen(
    onBack: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(){
        innerPadding ->

        BasicTopBar(
            text = "Delete Account",
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
        ){
            Spacer(modifier = Modifier.height(180.dp))

            PasswordTextField()

            Spacer(modifier = Modifier.height(40.dp))

            ContinueButton(onClick = onContinue)
        }
    }
}

@Composable
fun PasswordTextField(modifier: Modifier = Modifier){
    var password by remember { mutableStateOf("") }
    MyOutlinedTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = "Enter password",
        label = "Password",
        modifier = modifier
            .testTag("password_confirm_input")
    )
}

@Composable
fun ContinueButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    MyElevatedButton(
        modifier = modifier
            .testTag("delete_account_confirm_button"),
        text = "Continue",
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountPreview() {
    TheraPetTheme {
        DeleteAccountScreen(
            onBack = {},
            onContinue = {}
        )
    }
}