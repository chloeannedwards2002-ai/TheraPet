package com.example.therapet

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.therapet.helpers.RegisterScreenTestHelpers
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun register_button_disabled_before_all_fields_valid() {
        RegisterScreenTestHelpers.launchRegisterScreen(composeRule)

        composeRule
            .onNodeWithTag("register_button")
            .assertIsNotEnabled()
    }
}