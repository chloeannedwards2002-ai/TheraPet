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
 * Help support screen test
 */

class HelpSupportScreen {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun call_us_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("call_us_button")
            .assertIsDisplayed()
    }

    @Test
    fun email_us_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("email_us_button")
            .assertIsDisplayed()
    }

    @Test
    fun get_in_touch_text_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("get_in_touch")
            .assertIsDisplayed()
    }

    @Test
    fun instagram_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("instagram_button")
            .assertIsDisplayed()
    }

    @Test
    fun twitter_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("twitter_button")
            .assertIsDisplayed()
    }

    @Test
    fun whatsapp_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("whatsapp_button")
            .assertIsDisplayed()
    }

    @Test
    fun facebook_button_is_visible(){
        ScreenTestHelpers.launchHelpSupportScreen(composeRule)

        composeRule
            .onNodeWithTag("facebook_button")
            .assertIsDisplayed()
    }
}