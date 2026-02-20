package com.example.therapet.app.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.WatchlistRepository
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.components.bars.PetCareBar
import com.example.therapet.app.ui.viewmodel.AppointmentViewModel
import com.example.therapet.app.ui.viewmodel.PetViewModel
import com.example.therapet.app.ui.viewmodel.PetCareViewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

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
    petCareViewModel: PetCareViewModel,
    watchlistRepository: WatchlistRepository
) {
    val role by userViewModel.loggedInRole.collectAsState(initial = null)
    val session by sessionManager.session.collectAsState()
    val user by userViewModel.currentUser.collectAsState()

    if (role == null || session == null) return
    val userId = session!!.userid

    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = ViewModelFactory.AppointmentViewModelFactory(
            context = LocalContext.current,
            sessionManager = sessionManager,
            watchlistRepository = watchlistRepository
        )
    )

    val watchlistFlow: Flow<List<AccountUIModel>> =
        if (role == UserRole.THERAPIST) watchlistRepository.getWatchlistForTherapist(userId)
        else emptyFlow()

    val coroutineScope = rememberCoroutineScope()

    val watchlist by watchlistFlow.collectAsState(initial = emptyList<AccountUIModel>())

    var selectedAccount by remember { mutableStateOf<AccountUIModel?>(null) }

    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }

    fun removePatient(account: AccountUIModel) {
        coroutineScope.launch {
            watchlistRepository.removePatientFromWatchlist(userId, account.userid)
            selectedAccount = null

            snackbarHostState.showSnackbar(
                message = "${account.fullName} removed from watchlist"
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if (role == UserRole.PATIENT) {
                PetCareBar(
                    foodLevel = petCareViewModel.food.collectAsState().value,
                    waterLevel = petCareViewModel.water.collectAsState().value,
                    sleepLevel = petCareViewModel.sleep.collectAsState().value,
                    isSleeping = petCareViewModel.isSleeping.collectAsState().value,
                    onFoodIncrease = { petCareViewModel.increaseFood() },
                    onWaterIncrease = { petCareViewModel.increaseWater() },
                    onSleepClick = { petCareViewModel.startSleep() },
                    isHibernating = petCareViewModel.isHibernating.collectAsState().value
                )
            }
        }
    ) { innerPadding ->
        HomeScreen(
            role = role!!,
            user = user,
            petColourIndex = viewModel<PetViewModel>(
                factory = ViewModelFactory.PetViewModelFactory(LocalContext.current, userId)
            ).selectedColourIndex.collectAsState().value,
            modifier = Modifier.padding(innerPadding),
            onLogout = onLogout,
            onSettings = onSettings,
            onNotifs = onNotifs,
            onAppts = onAppts,
            onBookAppt = onBookAppt,
            onProfile = onProfile,
            watchlist = watchlist,
            selectedAccount = selectedAccount,
            onAccountRemove = { removePatient(it)},
            onAccountSelected = { selectedAccount = it }
        )
    }
}

