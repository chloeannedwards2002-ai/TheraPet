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
 * Welcome screen test
 */

class WelcomeScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun therapet_text_is_visible(){
        ScreenTestHelpers.launchWelcomeScreen(composeRule)

        composeRule
            .onNodeWithTag("therapet_text")
            .assertIsDisplayed()
    }

    @Test
    fun choose_register_button_is_visible(){
        ScreenTestHelpers.launchWelcomeScreen(composeRule)

        composeRule
            .onNodeWithTag("choose_register_button")
            .assertIsDisplayed()
    }

    @Test
    fun choose_login_button_is_visible(){
        ScreenTestHelpers.launchWelcomeScreen(composeRule)

        composeRule
            .onNodeWithTag("choose_login_button")
            .assertIsDisplayed()
    }
}