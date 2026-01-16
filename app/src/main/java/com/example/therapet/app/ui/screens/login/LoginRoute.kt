package com.example.therapet.app.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.therapet.app.ui.viewmodel.UserViewModel

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
    viewModel: UserViewModel
) {
    val loginResult by viewModel.loginResult.collectAsState(initial = null)

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LoginScreen(
        onLogin = { userId, password ->
            errorMessage = null
            viewModel.login(userId, password)
        },
        onRegisterNav = onRegisterNav,
        onBack = onBack,
        errorMessage = errorMessage
    )

    LaunchedEffect(loginResult) {
        when (loginResult) {
            true -> {
                onLoginSuccess()
                viewModel.clearLoginResult()
            }
            false -> {
                errorMessage = "Invalid user ID or password"
                viewModel.clearLoginResult()
            }
            null -> Unit
        }
    }
}
