package com.example.therapet.app.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.therapet.app.pet.CreatePetScreen
import com.example.therapet.app.settings.DeleteAccountConfirmScreen
import com.example.therapet.app.settings.DeleteAccountScreen
import com.example.therapet.app.settings.HelpSupportScreen
import com.example.therapet.app.home.HomeScreen
import com.example.therapet.app.auth.LoginScreen
import com.example.therapet.app.pet.PetSettingsScreen
import com.example.therapet.app.settings.PrivacyPolicyScreen
import com.example.therapet.app.home.ProfileScreen
import com.example.therapet.app.auth.RegisterScreen
import com.example.therapet.app.auth.ResetPasswordScreen
import com.example.therapet.app.settings.SettingsScreen
import com.example.therapet.app.auth.WelcomeScreen
import com.example.therapet.app.booking.AppointmentsScreen
import com.example.therapet.app.booking.BookAppointmentScreen
import com.example.therapet.app.booking.ChooseTherapistScreen
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Main activity utilising single activity architecture. Uses NavGraph and Routes to navigate through screens
 */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheraPetTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

