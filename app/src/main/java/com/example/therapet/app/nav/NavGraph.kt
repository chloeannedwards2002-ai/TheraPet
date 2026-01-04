package com.example.therapet.app.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.therapet.app.auth.LoginScreen
import com.example.therapet.app.auth.register.RegisterScreen
import com.example.therapet.app.auth.ResetPasswordScreen
import com.example.therapet.app.auth.WelcomeScreen
import com.example.therapet.app.booking.AppointmentsScreen
import com.example.therapet.app.booking.BookAppointmentScreen
import com.example.therapet.app.booking.ChooseTherapistScreen
import com.example.therapet.app.home.HomeScreen
import com.example.therapet.app.home.ProfileScreen
import com.example.therapet.app.pet.CreatePetScreen
import com.example.therapet.app.pet.PetSettingsScreen
import com.example.therapet.app.settings.DeleteAccountConfirmScreen
import com.example.therapet.app.settings.DeleteAccountScreen
import com.example.therapet.app.settings.HelpSupportScreen
import com.example.therapet.app.settings.PrivacyPolicyScreen
import com.example.therapet.app.settings.SettingsScreen

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

        composable(Routes.REGISTER) { // Composable for the Registration screen
            RegisterScreen(
                onRegister = { navController.navigate(Routes.CREATE_PET) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.LOGIN) { // Composable for the Login screen
            LoginScreen(
                onRegisterNav = { navController.navigate(Routes.REGISTER) },
                onLogin = { navController.navigate(Routes.HOME) },
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