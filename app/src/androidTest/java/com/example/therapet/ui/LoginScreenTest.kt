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
 * login screen test
 */

class LoginScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun login_text_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("login_text")
            .assertIsDisplayed()
    }

    @Test
    fun user_login_id_input_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("user_login_id_input")
            .assertIsDisplayed()
    }

    @Test
    fun login_password_input_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("login_password_input")
            .assertIsDisplayed()
    }

    @Test
    fun login_password_toggle_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("login_password_toggle")
            .assertIsDisplayed()
    }

    @Test
    fun remember_password_check_box_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("remember_password_check_box")
            .assertIsDisplayed()
    }

    @Test
    fun login_button_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("login_button")
            .assertIsDisplayed()
    }

    @Test
    fun to_register_button_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("register_button")
            .assertIsDisplayed()
    }

    @Test
    fun forgot_password_button_is_visible(){
        ScreenTestHelpers.launchLoginScreen(composeRule)

        composeRule
            .onNodeWithTag("forgot_password_button")
            .assertIsDisplayed()
    }
}