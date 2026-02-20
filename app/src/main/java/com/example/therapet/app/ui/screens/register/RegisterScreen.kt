package com.example.therapet.app.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.platform.testTag
import com.example.therapet.R
import com.example.therapet.app.auth.register.PasswordChecklist
import com.example.therapet.app.auth.register.PasswordValidator
import com.example.therapet.app.auth.register.RegisterValidation
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton
import com.example.therapet.app.ui.components.fields.input.CustomOutlinedTextField
import com.example.therapet.app.ui.components.fields.input.CustomPasswordTextField
import com.example.therapet.app.ui.components.fields.read_only.PrivacyPolicyDialog

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Registration screen UI
 */

@Composable
fun RegisterScreen(
    onRegister: (
            userId: String,
            firstName: String,
            surname: String,
            password: String
            ) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // field states (name and user id)
    var userId by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }

    // field states (password)
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val canRegister = RegisterValidation.canRegister(
        userId = userId,
        firstName = firstName,
        surname = surname,
        password = password,
        confirmPassword = confirmPassword
    )

    var showPrivacyPolicyDialog by remember { mutableStateOf(false) }

    Scaffold(

    ) { innerPadding ->

        BasicTopBar(
            text = "",
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("register_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(140.dp))

            Text(
                text = stringResource(R.string.register),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(40.dp))

            UserIDInput(
                value = userId,
                onValueChange = { userId = it }
            )

            if (userId.isNotEmpty() && !RegisterValidation.userIdIsValid(userId)) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Enter valid user ID",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    FirstNameInput(
                        value = firstName,
                        onValueChange = { firstName = it }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    SurnameInput(
                        value = surname,
                        onValueChange = { surname = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            PasswordInput(
                password = password,
                onPasswordChange = { password = it })

            Spacer(modifier = Modifier.height(20.dp))

            ConfPasswordInput(
                password = password,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            RegisterButton(
                onClick = {
                    showPrivacyPolicyDialog = true
                },
                enabled = canRegister
            )
        }

        if (showPrivacyPolicyDialog) {
            PrivacyPolicyDialog(
                onDismiss = { showPrivacyPolicyDialog = false },
                onAccepted = {
                    showPrivacyPolicyDialog = false
                    onRegister(userId, firstName, surname, password)
                }
            )
        }
    }
}

/*
    Input field composables
 */

// User ID input text field

@Composable
fun UserIDInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Enter User ID",
        label = "User ID",
        modifier = modifier.testTag("user_id_input")
    )
}

// First name input text field

@Composable
fun FirstNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "First Name",
        label = "First Name",
        modifier = modifier
            .testTag("first_name_input")
    )
}

// Surname input text field

@Composable
fun SurnameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
)
    {
    CustomOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Surname",
        label = "Surname",
        modifier = modifier
            .testTag("surname_input")
    )
}

// Password input text field

@Composable
fun PasswordInput(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val requirements = remember(password) {
        PasswordValidator.validate(password)
    }

    Column(modifier = modifier) {
        CustomPasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Password",
            label = "Password",
            modifier = Modifier.testTag("password_input"),
            testTag = "password_toggle"
        )

        if (password.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            PasswordChecklist(requirements)
        }
    }
}

// Confirm password input text field

@Composable
fun ConfPasswordInput(
    password: String,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val passwordsMatch =
        confirmPassword.isNotEmpty() && confirmPassword == password

    Column(modifier = modifier) {
        CustomPasswordTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "Confirm Password",
            label = "Confirm Password",
            modifier = Modifier.testTag("confirm_password_input"),
            testTag = "confirm_password_toggle"
        )

        if (confirmPassword.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (passwordsMatch)
                    "Passwords match!" else
                    "Passwords do not match!",
                style = MaterialTheme.typography.bodySmall,
                color = if (passwordsMatch)
                    Color(0xFF2E7D32) else
                    MaterialTheme.colorScheme.error
            )
        }
    }
}

//Register button -> Navigates to registration page
@Composable
fun RegisterButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    CustomFilledButton(
        onClick = onClick,
        enabled = enabled,
        text = stringResource(R.string.register),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("register_button")
    )
}


@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    TheraPetTheme {
        RegisterScreen(
            onRegister = { _, _, _, _ -> },
            onBack = {}
        )
    }
}