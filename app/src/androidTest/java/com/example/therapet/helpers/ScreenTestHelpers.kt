package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.ui.screens.register.RegisterScreen

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * These are helpers for screen UI tests
 */

object RegisterScreenTestHelpers {

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
}