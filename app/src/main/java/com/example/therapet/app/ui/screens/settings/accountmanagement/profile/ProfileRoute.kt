package com.example.therapet.app.ui.screens.settings.accountmanagement.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.therapet.app.ui.viewmodel.UserViewModel

/**
 * @author: Chloe Edwards
 * @date: 17/01/2026
 *
 * Retrieves the ViewModel and connects it to Profiel screen
 */

@Composable
fun ProfileRoute(
    onBack: () -> Unit,
    onEditPassword: () -> Unit,
    viewModel: UserViewModel
) {
    val user by viewModel.currentUser.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.loadCurrentUser()
    }

    ProfileScreen(
        onBack = onBack,
        onEditPassword = onEditPassword,
        firstName = user?.firstname.orEmpty(),
        surname = user?.surname.orEmpty(),
        userId = user?.userid.orEmpty(),
        mobile = null
    )
}