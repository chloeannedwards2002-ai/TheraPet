package com.example.therapet.app.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.PetViewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory

/**
 * @author: Chloe Edwards
 * @date: 13/01/2026
 *
 * Retrieves the ViewModel and connects it to HomeScreen
 */

@Composable
fun HomeRoute(
    onLogout: () -> Unit,
    onSettings: () -> Unit,
    onNotifs: () -> Unit,
    onAppts: () -> Unit,
    onBookAppt: () -> Unit,
    onProfile: () -> Unit,
    userViewModel: UserViewModel,
    sessionManager: SessionManager
) {
    val role by userViewModel.loggedInRole.collectAsState(initial = null)
    val session by sessionManager.session.collectAsState()
    val user by userViewModel.currentUser.collectAsState()

    val userId = session?.userid ?: return

    val petViewModel: PetViewModel = viewModel(
        factory = ViewModelFactory.PetViewModelFactory(
            context = LocalContext.current,
            userId = userId
        )
    )

    LaunchedEffect(Unit) {
        userViewModel.loadCurrentUser()
    }

    val petColourIndex by petViewModel.selectedColourIndex.collectAsState()

    if (role != null) {
        Scaffold { innerPadding ->
            HomeScreen(
                role = role!!,
                user = user,
                petColourIndex = petColourIndex,
                modifier = Modifier.padding(innerPadding),
                onLogout = onLogout,
                onSettings = onSettings,
                onNotifs = onNotifs,
                onAppts = onAppts,
                onBookAppt = onBookAppt,
                onProfile = onProfile
            )
        }
    }
}
