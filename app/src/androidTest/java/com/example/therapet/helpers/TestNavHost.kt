package com.example.therapet.helpers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.therapet.app.ui.screens.WelcomeScreen

/**
 * @Author: Chloe Edwards
 * @Date: 08/01/2026
 *
 * This is a fake navhost used for nav tests - now that viewmodel and routes are set up, fake screens are needed for pure nav testing
 */

class TestNavHost {
    @Composable

    fun TestNavHost(navController: NavHostController){
        NavHost(navController, startDestination = "test_welcome_screen"){
            composable("test_welcome_screen"){
                WelcomeScreen(
                    onLoginNav = { navController.navigate("login_screen") },
                    onRegisterNav = { navController.navigate("register_screen") }
                )
            }

        }
    }
}