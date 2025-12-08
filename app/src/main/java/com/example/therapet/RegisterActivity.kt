package com.example.therapet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.MyOutlinedTextField
import com.example.therapet.ui.theme.TheraPetTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import com.example.therapet.ui.theme.MyPasswordTextField

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheraPetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Registration(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Registration(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val activity = context as? Activity

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //TODO: Implement a TopAppBar to house all back buttons and other options dynamically
        IconButton(
            onClick = { activity?.finish() },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
                .testTag("back_button")
        ) {
            Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(200.dp))


        Text(text = "Register",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
            )

        Spacer(modifier = Modifier.height(40.dp))

        UserIDInput()

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Box(modifier = Modifier.weight(1f)){
                FirstNameInput()
            }
            Box(modifier = Modifier.weight(1f)){
                SurnameInput()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        PasswordInput()

        Spacer(modifier = Modifier.height(20.dp))

        ConfPasswordInput()

        Spacer(modifier = Modifier.height(20.dp))

        RegisterButton( onClick = { context.startActivity(
            Intent(context, CreatePetActivity::class.java)
        )} )
    }
}

// User ID input text field

@Composable
fun UserIDInput(modifier: Modifier = Modifier){
    var userID by remember { mutableStateOf("") }
    MyOutlinedTextField(
        value = userID,
        onValueChange = { userID = it },
        placeholder = "Enter User ID",
        label = "User ID",
        modifier = modifier
            .testTag("user_id_input")
    )
}

// First name input text field

@Composable
fun FirstNameInput(modifier: Modifier = Modifier){
    var firstName by remember { mutableStateOf("") }
    MyOutlinedTextField(
        value = firstName,
        onValueChange = { firstName = it },
        placeholder = "First Name",
        label = "First Name",
        modifier = modifier
            .testTag("first_name_input")
    )
}

// Surname input text field

@Composable
fun SurnameInput(modifier: Modifier = Modifier){
    var surname by remember { mutableStateOf("") }
    MyOutlinedTextField(
        value = surname,
        onValueChange = { surname = it },
        placeholder = "Surname",
        label = "Surname",
        modifier = modifier
            .testTag("surname_input")
    )
}

// Password input text field

@Composable
fun PasswordInput(modifier: Modifier = Modifier){
    var password by remember { mutableStateOf("") }

    MyPasswordTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = "Password",
        label = "Password",
        modifier = modifier
            .testTag("password_input"),
        toggleTestTag = "password_toggle"
    )
}

// Confirm password input text field

@Composable
fun ConfPasswordInput(modifier: Modifier = Modifier){
    var confPassword by remember { mutableStateOf("") }
    MyPasswordTextField(
        value = confPassword,
        onValueChange = { confPassword = it },
        placeholder = "Confirm Password",
        label = "Confirm Password",
        modifier = modifier
            .testTag("confirm_password_input"),
        toggleTestTag = "confirm_password_toggle"
    )
}

//Register button -> Navigates to registration page
@Composable
fun RegisterButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
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
    Registration()
    }
}