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
 * Reset password screen test
 */

class ResetPasswordScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun new_password_text_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("new_password_text")
            .assertIsDisplayed()
    }

    @Test
    fun enter_new_password_text_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("enter_new_password_text")
            .assertIsDisplayed()
    }

    @Test
    fun password_input_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("password_input")
            .assertIsDisplayed()
    }

    @Test
    fun confirm_password_text_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("confirm_password_text")
            .assertIsDisplayed()
    }

    @Test
    fun confirm_password_input_text_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("confirm_password_input")
            .assertIsDisplayed()
    }

    @Test
    fun reset_password_button_is_visible(){
        ScreenTestHelpers.launchResetPasswordScreen(composeRule)

        composeRule
            .onNodeWithTag("reset_password_button")
            .assertIsDisplayed()
    }
}