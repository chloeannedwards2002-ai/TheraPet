package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.screens.register.RegisterScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen

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

}