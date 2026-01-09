package com.example.therapet.app.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.therapet.app.ui.screens.settings.ResetPasswordScreen
import com.example.therapet.app.ui.screens.WelcomeScreen
import com.example.therapet.app.ui.screens.appts.AppointmentsScreen
import com.example.therapet.app.ui.screens.booking.BookAppointmentScreen
import com.example.therapet.app.ui.screens.booking.ChooseTherapistScreen
import com.example.therapet.app.ui.screens.home.HomeScreen
import com.example.therapet.app.ui.screens.settings.ProfileScreen
import com.example.therapet.app.ui.screens.pet.CreatePetScreen
import com.example.therapet.app.ui.screens.pet.PetSettingsScreen
import com.example.therapet.app.ui.screens.register.RegisterRoute
import com.example.therapet.app.ui.screens.settings.DeleteAccountConfirmScreen
import com.example.therapet.app.ui.screens.settings.DeleteAccountScreen
import com.example.therapet.app.ui.screens.settings.HelpSupportScreen
import com.example.therapet.app.ui.screens.settings.PrivacyPolicyScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen
import com.example.therapet.app.ui.screens.login.LoginRoute


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

        //Navgraph now doesn't call register screen anymore because of RegisterRoute
        composable(Routes.REGISTER) { // Composable for the Registration screen
            RegisterRoute(
                onBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Routes.CREATE_PET)
                }
            )
        }

        composable(Routes.LOGIN) { // Composable for the Login screen
            LoginRoute(
                onRegisterNav = { navController.navigate(Routes.REGISTER) },
                onLoginSuccess = { navController.navigate(Routes.HOME){
                    // Once logged in, cannot go back to login page
                    popUpTo(Routes.LOGIN) { inclusive = true }
                } },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.CREATE_PET) { // Composable for the pet creation screen
            CreatePetScreen(
                onCreatePet = { navController.navigate(Routes.HOME) }
            )
        }

        composable(Routes.HOME) { // Composable for the Home screen including navigation in NavDrawer
            HomeScreen(
                onLogout = {
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onProfile = { navController.navigate(Routes.PROFILE) },
                onSettings = { navController.navigate(Routes.SETTINGS) },
                onNotifs = { /* TODO: When notifs screen is created ! */ },
                onAppts = { navController.navigate(Routes.APPOINTMENTS) },
                onBookAppt = { navController.navigate(Routes.CHOOSE_THERAPIST) }
            )
        }

        composable(Routes.SETTINGS) { // Composable for the Settings screen
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onPetSettings = { navController.navigate(Routes.PET_SETTINGS) },
                onPrivacyPolicy = { navController.navigate(Routes.PRIVACY_POLICY) },
                onHelpSupport = { navController.navigate(Routes.HELP_SUPPORT) },
                onDeleteAccount = { navController.navigate(Routes.DELETE_ACCOUNT) }
            )
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