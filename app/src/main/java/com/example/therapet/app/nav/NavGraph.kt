package com.example.therapet.app.nav
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.WatchlistRepository
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.screens.WelcomeScreen
import com.example.therapet.app.ui.screens.appts.AppointmentsRoute
import com.example.therapet.app.ui.screens.booking.PatientAppointmentsRoute
import com.example.therapet.app.ui.screens.home.HomeRoute
import com.example.therapet.app.ui.screens.register.RegisterRoute
import com.example.therapet.app.ui.screens.settings.accountmanagement.DeleteAccountScreen
import com.example.therapet.app.ui.screens.settings.HelpSupportScreen
import com.example.therapet.app.ui.screens.settings.PrivacyPolicyScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen
import com.example.therapet.app.ui.screens.login.LoginRoute
import com.example.therapet.app.ui.screens.pet.CreatePetRoute
import com.example.therapet.app.ui.screens.pet.PetSettingsRoute
import com.example.therapet.app.ui.screens.settings.accountmanagement.DeleteAccountConfirmRoute
import com.example.therapet.app.ui.screens.settings.accountmanagement.profile.ProfileRoute
import com.example.therapet.app.ui.screens.settings.accountmanagement.password.ResetPasswordRoute
import com.example.therapet.app.ui.viewmodel.PetCareViewModel
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory
import com.example.therapet.app.ui.viewmodel.WatchlistViewModel


/**
 * @Author: Chloe Edwards
 * @Date: 21/12/2025
 *
 * The central file for navigating between destinations on the TheraPet app
 */

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.WELCOME
){
    val context = LocalContext.current

    val sessionManager = remember { SessionManager() }
    // bringing userviewmodel up above navigation
    val userViewModel: UserViewModel = viewModel(
        factory = ViewModelFactory.UserViewModelFactory(
            context = context,
            sessionManager
        )
    )

    /**
     * Repo for watchlist operations
     */
    val watchlistRepository = WatchlistRepository(
        watchlistDao = AppDatabase.getDatabase(LocalContext.current).watchlistDao(),
        userDao = AppDatabase.getDatabase(LocalContext.current).userDao()
    )

    /**
     * Pet care viewmodel for pet-related data
     */
    val petCareViewModel: PetCareViewModel = viewModel(
        factory = ViewModelFactory.PetCareViewModelFactory(
            context = context
        )
    )

    val role by userViewModel.loggedInRole.collectAsState(initial = null)

    /**
     * Nav host defining ALL routes and screens
     */
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Routes.WELCOME){ // Composable for the Welcome screen
            WelcomeScreen(
                onRegisterNav = { navController.navigate(Routes.REGISTER) },
                onLoginNav = { navController.navigate(Routes.LOGIN) }
            )
        }

        // User role determines what screen appears after registration
        composable(Routes.REGISTER) {
            RegisterRoute(
                onBack = { navController.popBackStack() },
                onRegisterSuccess = { role ->
                    when (role) {
                        UserRole.PATIENT -> navController.navigate(Routes.CREATE_PET)
                        UserRole.THERAPIST -> navController.navigate(Routes.HOME)
                    }
                },
                viewModel = userViewModel
            )
        }

        composable(Routes.LOGIN) {
            LoginRoute(
                onRegisterNav = { navController.navigate(Routes.REGISTER) },
                onLoginSuccess = { navController.navigate(Routes.HOME){
                    popUpTo(Routes.LOGIN) { inclusive = true }
                } },
                onBack = { navController.popBackStack() },
                viewModel = userViewModel
            )
        }

        composable(Routes.CREATE_PET) {
            CreatePetRoute(
                sessionManager = sessionManager,
                onCreatePet = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.CREATE_PET) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeRoute(
                onLogout = {
                    userViewModel.logout()
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onProfile = { navController.navigate(Routes.PROFILE) },
                onSettings = { navController.navigate(Routes.SETTINGS) },
                onNotifs = { /* TODO */ },
                onAppts = {
                    when (role) {
                        UserRole.PATIENT -> navController.navigate(Routes.PATIENT_APPOINTMENTS)
                        UserRole.THERAPIST -> navController.navigate(Routes.APPOINTMENTS)
                        else -> {}
                    }
                },
                onBookAppt = { navController.navigate(Routes.PATIENT_APPOINTMENTS) },
                sessionManager = sessionManager,
                userViewModel = userViewModel,
                petCareViewModel = petCareViewModel,
                watchlistRepository = watchlistRepository
            )
        }

        composable(Routes.SETTINGS) { // Composable for the Settings screen
            //TODO: Duplicated logic is not ideal will adjust later
            val role by userViewModel.loggedInRole.collectAsState()

            if (role != null) {
                SettingsScreen(
                    onBack = { navController.popBackStack() },
                    onPetSettings = { navController.navigate(Routes.PET_SETTINGS) },
                    onPrivacyPolicy = { navController.navigate(Routes.PRIVACY_POLICY) },
                    onHelpSupport = { navController.navigate(Routes.HELP_SUPPORT) },
                    onDeleteAccount = { navController.navigate(Routes.DELETE_ACCOUNT) },
                    role = role!!
                )
            }

        }

        composable(Routes.APPOINTMENTS) {
            if (role != null) {
                AppointmentsRoute(
                    role = role!!,
                    sessionManager = sessionManager,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.PATIENT_APPOINTMENTS) {
            PatientAppointmentsRoute(
                onBack = { navController.popBackStack() },
                sessionManager = sessionManager
            )
        }

        composable(Routes.PROFILE) {
            ProfileRoute(
                onBack = { navController.popBackStack() },
                onEditPassword = { navController.navigate(Routes.RESET_PASSWORD) },
                viewModel = userViewModel
            )
        }

        composable(Routes.RESET_PASSWORD) {
            ResetPasswordRoute(
                onBack = { navController.popBackStack() },
                onSuccess = { navController.popBackStack() },
                viewModel = userViewModel
            )
        }

        composable(Routes.PET_SETTINGS) {
            PetSettingsRoute(
                onBack = { navController.popBackStack() },
                sessionManager = sessionManager,
                petCareViewModel = petCareViewModel
            )
        }

        composable(Routes.PRIVACY_POLICY) { // Composable for the Privacy policy screen
            PrivacyPolicyScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.HELP_SUPPORT) { // Composable for the Help & Support screen
            HelpSupportScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.DELETE_ACCOUNT) {
            DeleteAccountScreen(
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate(Routes.DELETE_ACCOUNT_CONFIRM) },
                validatePassword = { enteredPassword ->
                    userViewModel.verifyPassword(enteredPassword)
                }
            )
        }


        composable(Routes.DELETE_ACCOUNT_CONFIRM) { // Composable for the Delete account confirmation screen
            DeleteAccountConfirmRoute(
                onBack = { navController.popBackStack() },
                onLoggedOut = {
                    navController.navigate(Routes.WELCOME){
                        popUpTo(0)
                    }
                },
                viewModel = userViewModel,
                sessionManager = sessionManager
            )
        }
    }
}