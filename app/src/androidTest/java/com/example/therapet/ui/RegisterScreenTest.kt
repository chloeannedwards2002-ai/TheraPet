package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 09/01/2026
 *
 * Register screen UI tests (to be updated)
 */

class RegisterScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun register_button_disabled_before_all_fields_valid() {
        ScreenTestHelpers.launchRegisterScreen(composeRule)

        composeRule
            .onNodeWithTag("register_button")
            .assertIsNotEnabled()
    }
}