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
import com.example.therapet.app.ui.viewmodel.PetCareViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory

/**
 * @author: Chloe Edwards
 * @date: 15/02/2026
 *
 * Pet settings screen route
 */

@Composable
fun PetSettingsRoute(
    sessionManager: SessionManager,
    petCareViewModel: PetCareViewModel,
    onBack: () -> Unit
) {
    val sessionState by sessionManager.session.collectAsState()
    val currentSession = sessionState ?: return

    val isHibernating by petCareViewModel.isHibernating.collectAsState()

    Scaffold { innerPadding ->
        PetSettingsScreen(
            hibernationEnabled = isHibernating,
            onHibernationChanged = { enabled ->
                petCareViewModel.setHibernation(enabled)
            },
            onBack = onBack,
            modifier = Modifier.padding(innerPadding)
        )
    }
}