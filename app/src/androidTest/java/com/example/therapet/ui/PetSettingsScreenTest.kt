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
 * Pet settings screen test
 */

class PetSettingsScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun toggle_appointment_reminders_button_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("appointment_reminders_toggle")
            .assertIsDisplayed()
    }

    @Test
    fun toggle_notifs_text_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("turn_off_notifs_text")
            .assertIsDisplayed()
    }

    @Test
    fun toggle_pet_sounds_button_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_sounds_toggle")
            .assertIsDisplayed()
    }

    @Test
    fun toggle_pet_sounds_text_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("toggle_pet_sounds_text")
            .assertIsDisplayed()
    }

    @Test
    fun toggle_hibernation_button_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("hibernation_toggle")
            .assertIsDisplayed()
    }

    @Test
    fun toggle_hibernation_text_is_visible(){
        ScreenTestHelpers.launchPetSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("toggle_hibernation_text")
            .assertIsDisplayed()
    }
}