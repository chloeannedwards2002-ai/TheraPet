package com.example.therapet.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.therapet.CreatePetScreen
import com.example.therapet.HomeScreen
import com.example.therapet.LoginScreen
import com.example.therapet.ProfileScreen
import com.example.therapet.RegisterScreen
import com.example.therapet.ResetPasswordScreen
import com.example.therapet.SettingsScreen
import com.example.therapet.WelcomeScreen
import com.example.therapet.booking.AppointmentsScreen
import com.example.therapet.booking.BookAppointmentScreen
import com.example.therapet.booking.ChooseTherapistScreen
import com.example.therapet.ui.theme.TheraPetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheraPetTheme {
                val navController = rememberNavController()
                TheraPet(navController)
            }
        }
    }
}

@Composable
fun TheraPet(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "welcome_screen"
    ) {
        composable("welcome_screen") {
            WelcomeScreen(
                onRegisterNav = { navController.navigate("register") },
                onLoginNav = { navController.navigate("login") }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegister = { navController.navigate("create_pet")},
                onBack = { navController.popBackStack() }
            )
        }
        composable("login") {
            LoginScreen(
                onRegisterNav = {navController.navigate("register")},
                onLogin = {navController.navigate("home_screen")},
                onBack = {navController.popBackStack()}
            )
        }
        composable("create_pet") {
            CreatePetScreen(
                onCreatePet = {navController.navigate("home_screen")}
            )
        }
        composable("home_screen") {
            HomeScreen(
                onLogout = {
                navController.navigate("welcome_screen") {
                    popUpTo("home_screen") { inclusive = true }
                    launchSingleTop = true
                }
            },
                onProfile = {
                    navController.navigate("profile_screen")
                            },

                onSettings = {
                    navController.navigate("settings_screen")
                },

                //TODO: Add notifs screen
                onNotifs = {
                    navController.navigate("")
                },

                onAppts = {
                    navController.navigate("appointments_screen")
                },

                onBookAppt = { navController.navigate("choose_therapist_screen")}
            )
        }

        composable("appointments_screen"){
            AppointmentsScreen(onBack = {navController.popBackStack()},
                onBookAppt = {navController.navigate("book_appointment_screen")})
        }

        composable("settings_screen"){
            SettingsScreen(onBack = {navController.popBackStack()})
        }

        composable("choose_therapist_screen"){
            ChooseTherapistScreen(onBack = {navController.popBackStack()
            },
                onContinue = {navController.navigate("book_appointment_screen")})
        }

        composable("book_appointment_screen") {
            BookAppointmentScreen(
                onBack = {navController.popBackStack()},
                onBook = {/*Show popup*/}
            )
        }

        composable("profile_screen") {
            ProfileScreen(
                onBack = {navController.popBackStack()},
                onEditPassword = {navController.navigate("reset_password_screen")}
            )
        }

        composable("reset_password_screen"){
            ResetPasswordScreen(
                onBack = {navController.popBackStack()},
                onResetPassword = {/*TODO: reset password functionality*/}
            )
        }
    }
}
