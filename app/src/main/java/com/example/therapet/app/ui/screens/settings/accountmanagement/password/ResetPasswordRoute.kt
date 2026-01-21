package com.example.therapet.app.ui.screens.settings.accountmanagement.password

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
 * Reset password Route UI
 */

@Composable
fun ResetPasswordRoute(
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: UserViewModel
) {
    var error by remember { mutableStateOf<String?>(null) }
    val result by viewModel.resetPasswordResult.collectAsState()

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

    LaunchedEffect(result) {
        when (result) {
            true -> {
                viewModel.clearResetPasswordResult()
                onSuccess()
            }
            false -> {
                error = "Failed to reset password"
                viewModel.clearResetPasswordResult()
            }
            null -> Unit
        }
    }
}