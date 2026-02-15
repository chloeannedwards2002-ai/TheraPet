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
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.components.bars.PetCareBar
import com.example.therapet.app.ui.viewmodel.PetViewModel
import com.example.therapet.app.ui.viewmodel.PetCareViewModel
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
    sessionManager: SessionManager,
    petCareViewModel: PetCareViewModel
) {
    val role by userViewModel.loggedInRole.collectAsState(initial = null)
    val session by sessionManager.session.collectAsState()
    val user by userViewModel.currentUser.collectAsState()

    // Wait until role and session are loaded
    if (role == null || session == null) {
        return
    }

    val userId = session!!.userid

    val petViewModel: PetViewModel = viewModel(
        factory = ViewModelFactory.PetViewModelFactory(
            context = LocalContext.current,
            userId = userId
        )
    )

    val petColourIndex by petViewModel.selectedColourIndex.collectAsState()
    val foodLevel by petCareViewModel.food.collectAsState()
    val waterLevel by petCareViewModel.water.collectAsState()

    val sleepLevel by petCareViewModel.sleep.collectAsState()
    val isSleeping by petCareViewModel.isSleeping.collectAsState()

    val isHibernating by petCareViewModel.isHibernating.collectAsState()

    val startSleep = { petCareViewModel.startSleep() }

    val increaseFood = { petCareViewModel.increaseFood() }
    val increaseWater = { petCareViewModel.increaseWater() }

    LaunchedEffect(Unit) { userViewModel.loadCurrentUser() }

    Scaffold(
        bottomBar = {
            if (role == UserRole.PATIENT) {
                PetCareBar(
                    modifier = Modifier,
                    foodLevel = foodLevel,
                    waterLevel = waterLevel,
                    sleepLevel = sleepLevel,
                    isSleeping = isSleeping,
                    onFoodIncrease = increaseFood,
                    onWaterIncrease = increaseWater,
                    onSleepClick = startSleep,
                    isHibernating = isHibernating
                )
            }
        }
    ) { innerPadding ->
        HomeScreen(
            role = role!!,
            user = user,
            petColourIndex = petColourIndex,
            modifier = Modifier.padding(innerPadding),
            foodLevel = foodLevel,
            waterLevel = waterLevel,
            sleepLevel = sleepLevel,
            isSleeping = isSleeping,
            onFoodIncrease = increaseFood,
            onWaterIncrease = increaseWater,
            onSleepClick = startSleep,
            onLogout = onLogout,
            onSettings = onSettings,
            onNotifs = onNotifs,
            onAppts = onAppts,
            onBookAppt = onBookAppt,
            onProfile = onProfile,
            isHibernating = isHibernating
        )
    }
}
