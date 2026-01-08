package com.example.therapet.app.ui.screens.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory


/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * Retrieves the ViewModel and connects it to RegisterScreen.kt
 *  1. Calls viewModel.register() when the user clicks register button
 *  2. Gets the registerResult
 *  3. Reacts to a registration success or failure
 *  4. Passes these results back to RegisterScreen
 */

@Composable
fun RegisterRoute(
    onBack: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: UserViewModel = viewModel(
        factory = ViewModelFactory.UserViewModelFactory(LocalContext.current)
    )
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val registerResult by viewModel.registerResult.collectAsState(initial = null)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        RegisterScreen(
            onBack = onBack,
            onRegister = { userId, firstName, surname, password ->
                viewModel.register(
                    userid = userId,
                    firstname = firstName,
                    surname = surname,
                    password = password
                )
            },
            modifier = Modifier.padding(innerPadding)
        )
    }

    LaunchedEffect(registerResult) {
        when (registerResult) {
            true -> {
                onRegisterSuccess()
                viewModel.clearRegisterResult()
            }
            false -> {
                snackbarHostState.showSnackbar("User ID is already in use")
                viewModel.clearRegisterResult()
            }
            null -> Unit
        }
    }
}


