package com.example.therapet.app.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.session.SessionManager
import com.example.therapet.app.ui.screens.settings.ResetPasswordScreen
import com.example.therapet.app.ui.screens.WelcomeScreen
import com.example.therapet.app.ui.screens.appts.AppointmentsScreen
import com.example.therapet.app.ui.screens.booking.BookAppointmentScreen
import com.example.therapet.app.ui.screens.booking.ChooseTherapistScreen
import com.example.therapet.app.ui.screens.home.HomeRoute
import com.example.therapet.app.ui.screens.settings.ProfileScreen
import com.example.therapet.app.ui.screens.pet.PetSettingsScreen
import com.example.therapet.app.ui.screens.register.RegisterRoute
import com.example.therapet.app.ui.screens.settings.DeleteAccountConfirmScreen
import com.example.therapet.app.ui.screens.settings.DeleteAccountScreen
import com.example.therapet.app.ui.screens.settings.HelpSupportScreen
import com.example.therapet.app.ui.screens.settings.PrivacyPolicyScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen
import com.example.therapet.app.ui.screens.login.LoginRoute
import com.example.therapet.app.ui.screens.pet.CreatePetRoute
import com.example.therapet.app.ui.viewmodel.UserViewModel
import com.example.therapet.app.ui.viewmodel.ViewModelFactory


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
                onAppts = { navController.navigate(Routes.APPOINTMENTS) },
                onBookAppt = { navController.navigate(Routes.CHOOSE_THERAPIST) },
                sessionManager = sessionManager,
                userViewModel = userViewModel
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

        composable(Routes.APPOINTMENTS) { // Composable for the Appointments screen
            AppointmentsScreen(
                onBack = { navController.popBackStack() },
                onBookAppt = { navController.navigate(Routes.BOOK_APPOINTMENT) }
            )
        }

        composable(Routes.CHOOSE_THERAPIST) { // Composable for the Choose Therapist screen
            ChooseTherapistScreen(
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate(Routes.BOOK_APPOINTMENT) }
            )
        }

        composable(Routes.BOOK_APPOINTMENT) { // Composable for the Book Appointments screen
            BookAppointmentScreen(
                onBack = { navController.popBackStack() },
                onBook = { /* TODO: when booking functionality is implemented !! */ }
            )
        }

        composable(Routes.PROFILE) { // Composable for the Profile screen
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onEditPassword = { navController.navigate(Routes.RESET_PASSWORD) }
            )
        }

        composable(Routes.RESET_PASSWORD) { // Composable for the Reset password screen
            ResetPasswordScreen(
                onBack = { navController.popBackStack() },
                onResetPassword = { /* TODO: when reset password functionality is implemented !! */ }
            )
        }

        composable(Routes.PET_SETTINGS) { // Composable for the Pet settings screen
            PetSettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.PRIVACY_POLICY) { // Composable for the Privacy policy screen
            PrivacyPolicyScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.HELP_SUPPORT) { // Composable for the Help & Support screen
            HelpSupportScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.DELETE_ACCOUNT) { // Composable for the Delete account screen
            DeleteAccountScreen(
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate(Routes.DELETE_ACCOUNT_CONFIRM) }
            )
        }

        composable(Routes.DELETE_ACCOUNT_CONFIRM) { // Composable for the Delete account confirmation screen
            DeleteAccountConfirmScreen(
                onBack = { navController.popBackStack() },
                onDeleteAccount = { /* TODO: when delete account functionality is implemented !! */ }
            )
        }
    }
}