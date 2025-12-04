package com.example.therapet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheraPetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Composing the full column

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to ${stringResource(R.string.app_name)}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        RegisterButton( onClick = {} )
        LoginButton( onClick = {} )
    }
}

//Register button -> Navigates to registration page
@Composable
fun RegisterButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
        text = stringResource(R.string.register),
        modifier = Modifier.fillMaxWidth(0.5F).padding(top = 20.dp)

    )
}

//Login button -> Navigates to login page
@Composable
fun LoginButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
        text = stringResource(R.string.login),
        modifier = Modifier.fillMaxWidth(0.5F).padding(top = 20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheraPetTheme {
        Greeting()
    }
}