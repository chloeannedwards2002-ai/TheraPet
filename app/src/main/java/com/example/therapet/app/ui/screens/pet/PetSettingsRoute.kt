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
 *
 * Observes current sessio to get user ID
 */

@Composable
fun PetSettingsRoute(
    sessionManager: SessionManager, // Session manager
    petCareViewModel: PetCareViewModel, // Managing the pet's care settings
    onBack: () -> Unit // Callback when user clicks back
) {
    /**
     * Observe current session
     */
    val sessionState by sessionManager.session.collectAsState()
    val currentSession = sessionState ?: return

    /**
     * Observe whether hibernation mode is enabled
     */
    val isHibernating by petCareViewModel.isHibernating.collectAsState()

    val remindersEnabled by petCareViewModel.remindersEnabled.collectAsState()

    Scaffold { innerPadding ->
        PetSettingsScreen(
            hibernationEnabled = isHibernating,
            onHibernationChanged = { enabled ->
                petCareViewModel.setHibernation(enabled)
            },
            onBack = onBack,
            modifier = Modifier.padding(innerPadding),
            onRemindersChanged = {petCareViewModel.setRemindersEnabled(it)},
            remindersEnabled = remindersEnabled
        )
    }
}