package com.example.therapet.app.ui.screens.settings.accountmanagement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.UserViewModel

/**
 * @author: Chloe Edwards
 * @date: 16/01/2026
 *
 * Delete account confirmation route
 */

@Composable
fun DeleteAccountConfirmRoute(
    onBack: () -> Unit,
    onLoggedOut: () -> Unit,
    viewModel: UserViewModel,
    sessionManager: SessionManager
){
    val session by sessionManager.session.collectAsState()

    DeleteAccountConfirmScreen(
        onBack = onBack,
        onDeleteAccount = {
            viewModel.deleteAccount()
        }
    )

    LaunchedEffect(session){
        if(session == null){
            onLoggedOut()
        }
    }
}

