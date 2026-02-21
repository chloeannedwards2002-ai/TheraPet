package com.example.therapet.app.ui.screens.login

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.R
import com.example.therapet.app.auth.login.LoginValidation
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton
import com.example.therapet.app.ui.components.buttons.general.CustomTextButton
import com.example.therapet.app.ui.components.buttons.toggle.CheckBox
import com.example.therapet.app.ui.components.fields.input.CustomOutlinedTextField
import com.example.therapet.app.ui.components.fields.input.CustomPasswordTextField

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Login screen UI
 *
 * Handles navigation by enabling login button only when all inputs are valid
 */

@Composable
fun LoginScreen(
    onLogin: (userId: String, password: String) -> Unit, // Callback invoked when login button is clicked
    onRegisterNav: () -> Unit, // Callback to navigate to reg screen
    onBack: () -> Unit, // Callback for back navigation
    errorMessage: String?, // Optional error message to display
    modifier: Modifier = Modifier
) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //Checking if all inputs are filled in
    val canLogin = LoginValidation.canLogin(
        userId = userId,
        password = password
    )

    Scaffold(

    ) { innerPadding ->

        /**
         * Top bar with back navigation
         */
        BasicTopBar(
            text = "",
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("login_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(140.dp))

            Text(
                text = stringResource(R.string.login),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.testTag("login_text")
            )

            Spacer(modifier = Modifier.height(40.dp))

            UserLoginIDInput(value = userId, onValueChange = { userId = it })

            Spacer(modifier = Modifier.height(20.dp))

            LoginPasswordInput(
                value = password,
                onValueChange = { password = it }
            )
            /**
             * Show error message if login failed
             */
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                        .testTag("login_error_text")
                )
            }
            /**
             * Remember password checkbox
             */
            RememberPasswordCheckBox(
                modifier = Modifier.align(Alignment.Start)
            )

            /**
             * Login button
             */
            LoginButton(
                onClick = {
                    onLogin(userId, password)
                },
                enabled = canLogin
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "or",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )

            // Go to the register screen
            ToRegisterButton(
                onClick = onRegisterNav
            )

            /**
             * Navigate to forgot password - out of scope
             */
            ForgotPasswordButton(
                onClick = { /*navController.navigate("forgot_password")*/ })
        }
    }
}

/**
 * Input fields and buttons for login screen
 */

// User ID input
@Composable
fun UserLoginIDInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier){
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Enter User ID",
        label = "User ID",
        modifier = modifier
            .testTag("user_login_id_input")
    )
}

// Password input
@Composable
fun LoginPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    CustomPasswordTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Password",
        label = "Password",
        modifier = modifier
            .testTag("login_password_input"),
        testTag = "login_password_toggle"
    )
}

// Remember password checkbox
@Composable
fun RememberPasswordCheckBox(modifier: Modifier = Modifier){
    var checked by remember { mutableStateOf(false) }
    CheckBox(
        label = "Remember password",
        checked = checked,
        onCheckedChange = {checked = it},
        modifier = Modifier
            .testTag("remember_password_check_box")
    )
}

// Login button
@Composable
fun LoginButton(onClick: () -> Unit,
                enabled: Boolean){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.login),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("login_button"),
        enabled = enabled
    )
}

// Register button
@Composable
fun ToRegisterButton(onClick: () -> Unit) {
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.register),
        modifier = Modifier
            .fillMaxWidth(fraction=0.5f)
            .padding(top = 20.dp)
            .testTag("register_button")
    )
}

// Forgot password? button
@Composable
fun ForgotPasswordButton(onClick: () -> Unit){
    CustomTextButton(
        onClick = onClick,
        text = stringResource(R.string.forgot_password),
        modifier = Modifier.
        fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("forgot_password_button")
    )
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TheraPetTheme {
        LoginScreen(
            onLogin = {_, _ ->},
            onBack = {},
            onRegisterNav = {},
            errorMessage = null
        )
    }
}