package com.example.therapet.app.ui.screens.pet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.viewmodel.PetViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Create pet screen route
 */

@Composable
fun CreatePetRoute(
    sessionManager: SessionManager,
    onCreatePet: () -> Unit
) {
    val sessionState by sessionManager.session.collectAsState()
    val currentSession = sessionState ?: return

    val petViewModel: PetViewModel = viewModel(
        factory = ViewModelFactory.PetViewModelFactory(
            context = LocalContext.current,
            userId = currentSession.userid
        )
    )

    val selectedColourIndex by petViewModel.selectedColourIndex.collectAsState()

    Scaffold { innerPadding ->
        CreatePetScreen(
            selectedColourIndex = selectedColourIndex,
            onColourSelected = petViewModel::selectColour,
            onCreatePet = onCreatePet,
            modifier = Modifier.padding(innerPadding)
        )
    }
}