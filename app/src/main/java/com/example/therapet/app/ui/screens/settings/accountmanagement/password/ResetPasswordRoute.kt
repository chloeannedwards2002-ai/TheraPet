package com.example.therapet.app.ui.screens.settings.accountmanagement.password

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
 * @date: 21/01/2026
 *
 * Reset password Route
 *
 * Handles logic for resetting a users password
 */

@Composable
fun ResetPasswordRoute(
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: UserViewModel
) {
    var error by remember { mutableStateOf<String?>(null) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    val result by viewModel.resetPasswordResult.collectAsState()

    /**
     * Load reset password UI
     */
    ResetPasswordScreen(
        onBack = onBack,
        errorMessage = error,
        onResetPassword = { password, confirm ->
            when {
                password.isBlank() || confirm.isBlank() ->
                    error = "Please enter a new password"

                password != confirm ->
                    error = "Passwords do not match"

                else -> {
                    error = null
                    viewModel.resetPassword(password)
                }
            }
        }
    )
    /**
     * Success dialog
     */
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Success") },
            text = { Text("Password reset successfully") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccessDialog = false
                        onSuccess()
                    }
                ) {
                    Text("Ok")
                }
            }
        )
    }

    /**
     * Observe result from viewmodel
     */
    LaunchedEffect(result) {
        when (result) {
            true -> {
                showSuccessDialog = true
                viewModel.clearResetPasswordResult()
            }
            false -> {
                error = "Password reset failed"
                viewModel.clearResetPasswordResult()
            }
            null -> Unit
        }
    }
}