package com.example.therapet.app.ui.screens.login

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
 * @date: 09/01/2026
 *
 * Retrieves the ViewModel and connects it to loginScreen.kt
 *  1. Calls viewModel.login() when the user clicks login button
 *  2. Gets the loginResult
 *  3. Reacts to a login success or failure
 *  4. Passes these results back to LoginScreen
 */

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onRegisterNav: () -> Unit,
    onBack: () -> Unit,
    viewModel: UserViewModel = viewModel(
        factory = ViewModelFactory.UserViewModelFactory(LocalContext.current)
    )
){
    val snackbarHostState = remember { SnackbarHostState() }
    val loginResult by viewModel.loginResult.collectAsState(initial = null)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)} )
    {
        innerPadding ->

        LoginScreen(
            { userId, password ->
                viewModel.login(userId, password)
            },
            onRegisterNav = onRegisterNav,
            onBack = onBack,
            modifier = Modifier.padding(innerPadding)
        )
    }

    LaunchedEffect(loginResult){
        when(loginResult){
            true -> {
                onLoginSuccess()
                viewModel.clearLoginResult()
            }
            false -> {
                snackbarHostState.showSnackbar("Invalid user ID or password")
                viewModel.clearLoginResult()
            }
            null -> Unit
        }
    }

}
