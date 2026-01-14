package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 14/01/2026
 *
 * Delete account screen test
 */

class DeleteAccountScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun password_confirm_input_is_visible(){
        ScreenTestHelpers.launchDeleteAccountScreen(composeRule)

        composeRule
            .onNodeWithTag("password_confirm_input")
            .assertIsDisplayed()
    }

    @Test
    fun delete_account_continue_button_is_visible(){
        ScreenTestHelpers.launchDeleteAccountScreen(composeRule)

        composeRule
            .onNodeWithTag("delete_account_confirm_button")
            .assertIsDisplayed()
    }
}