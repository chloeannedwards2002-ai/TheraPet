package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.auth.register.RegisterScreen

object RegisterScreenTestHelpers {

    fun launchRegisterScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ) {
        composeRule.setContent {
            RegisterScreen(
                onRegister = {},
                onBack = {}
            )
        }
    }
}