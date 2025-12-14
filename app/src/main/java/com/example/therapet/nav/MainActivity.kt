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
import com.example.therapet.RegisterScreen
import com.example.therapet.WelcomeScreen
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
            HomeScreen(onLogout = {navController.navigate("welcome_screen"){
                popUpTo("home_screen") {
                    inclusive = true
                }
                launchSingleTop = true
            } })
        }
    }
}
