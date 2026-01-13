package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.screens.home.HomeScreen
import com.example.therapet.app.ui.screens.pet.CreatePetScreen
import com.example.therapet.app.ui.screens.register.RegisterScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * These are helpers for screen UI tests e.g. launching screens
 */

object ScreenTestHelpers {

    //register screen
    fun launchRegisterScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ) {
        composeRule.setContent {
            RegisterScreen(
                onRegister = {_, _, _, _ -> },
                onBack = {}
            )
        }
    }

    //settings screen (patient state)
    fun launchPatientSettingsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            SettingsScreen(
                role = UserRole.PATIENT,
                onBack = {},
                onPetSettings = {},
                onPrivacyPolicy = {},
                onHelpSupport = {},
                onDeleteAccount = {}
            )
        }
    }

    //settings screen (therapist state)
    fun launchTherapistSettingsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            SettingsScreen(
                role = UserRole.THERAPIST,
                onBack = {},
                onPetSettings = {},
                onPrivacyPolicy = {},
                onHelpSupport = {},
                onDeleteAccount = {}
            )
        }
    }

    //create pet screen
    fun launchCreatePetScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            TheraPetTheme {
                CreatePetScreen(
                    selectedColourIndex = 0,
                    onColourSelected = {},
                    onCreatePet = {}
                )
            }
        }
    }

    //homescreen
    fun launchPatientHomeScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>,
        role: UserRole = UserRole.PATIENT,
        petColourIndex: Int = 0
    ){
        composeRule.setContent {
            TheraPetTheme {
                HomeScreen(
                    role = role,
                    petColourIndex = petColourIndex,
                    onLogout = {},
                    onSettings = {},
                    onNotifs = {},
                    onAppts = {},
                    onBookAppt = {},
                    onProfile = {}
                )
            }
        }
    }

    fun launchTherapistHomeScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>,
        role: UserRole = UserRole.THERAPIST,
        petColourIndex: Int = 0
    ){
        composeRule.setContent {
            TheraPetTheme {
                HomeScreen(
                    role = role,
                    petColourIndex = petColourIndex,
                    onLogout = {},
                    onSettings = {},
                    onNotifs = {},
                    onAppts = {},
                    onBookAppt = {},
                    onProfile = {}
                )
            }
        }
    }

}