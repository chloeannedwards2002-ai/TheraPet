package com.example.therapet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.MyOutlinedTextField
import com.example.therapet.ui.theme.MyPasswordTextField
import com.example.therapet.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.ui.theme.MyCheckBox
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.MyTextButton

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheraPetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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

        Text(text = "Login",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(40.dp))
        
        UserLoginIDInput()

        Spacer(modifier = Modifier.height(20.dp))

        LoginPasswordInput()

        RememberPasswordCheckBox(
            modifier = Modifier.align(Alignment.Start)
        )

        ToRegisterButton(onClick = {context.startActivity(
            Intent(context, RegisterActivity::class.java)
        )})

        Spacer(modifier = Modifier.height(height = 5.dp))

        Text(text = "or",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )

        //TODO: Navigate to the Main Therapet Screen
        LoginButton(onClick = {} )

        ForgotPasswordButton(onClick = {})
    }
}

// User ID input
@Composable
fun UserLoginIDInput(modifier: Modifier = Modifier){
    var loginID by remember { mutableStateOf("") }
    MyOutlinedTextField(
        value = loginID,
        onValueChange = { loginID = it },
        placeholder = "Enter User ID",
        label = "User ID",
        modifier = modifier
            .testTag("user_login_id_input")
    )
}

// Password input
@Composable
fun LoginPasswordInput(modifier: Modifier = Modifier){
    var password by remember { mutableStateOf("") }

    MyPasswordTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = "Password",
        label = "Password",
        modifier = modifier
            .testTag("login_password_input"),
        toggleTestTag = "login_password_toggle"
    )
}

// Remember password checkbox
@Composable
fun RememberPasswordCheckBox(modifier: Modifier = Modifier){
    var checked by remember { mutableStateOf(false) }
    MyCheckBox(
        label = "Remember password",
        checked = checked,
        onCheckedChange = {checked = it},
        modifier = modifier
    )
}

// Login button
@Composable
fun LoginButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
        text = stringResource(R.string.register),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 20.dp)
            .testTag("login_button")
    )
}

// Register button
@Composable
fun ToRegisterButton(onClick: () -> Unit) {
    MyFilledButton(
        onClick = onClick,
        text = stringResource(R.string.login),
        modifier = Modifier
            .fillMaxWidth(fraction=0.5f)
            .padding(top = 20.dp)
            .testTag("register_button")
    )
}

// Forgot password? button
@Composable
fun ForgotPasswordButton(onClick: () -> Unit){
    MyTextButton(
        onClick = onClick,
        text = "Forgot password?",
        modifier = Modifier.fillMaxWidth(0.5F).padding(top = 20.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TheraPetTheme {
        Login()
    }
}