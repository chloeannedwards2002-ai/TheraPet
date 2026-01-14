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
 * @date: 11/01/2026
 *
 * Settings screen UI tests (to be updated)
 *
 * 1. Patient role shows the pet settings button
 * 2. Therapist role doesn't show the pet settings button
 */

class SettingsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun patient_role_shows_pet_settings_button(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_settings_button")
            .assertIsDisplayed()
    }

    @Test
    fun therapist_role_does_not_shows_pet_settings_button(){
        ScreenTestHelpers.launchTherapistSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_settings_button")
            .assertDoesNotExist()
    }

    @Test
    fun settings_language_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("language_button")
            .assertIsDisplayed()
    }

    @Test
    fun notification_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("notification_button")
            .assertIsDisplayed()
    }

    @Test
    fun backup_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("backup_button")
            .assertIsDisplayed()
    }

    @Test
    fun language_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("language_button")
            .assertIsDisplayed()
    }

    @Test
    fun security_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("security_button")
            .assertIsDisplayed()
    }

    @Test
    fun permissions_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("permissions_button")
            .assertIsDisplayed()
    }

    @Test
    fun privacy_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("privacy_policy_button")
            .assertIsDisplayed()
    }

    @Test
    fun help_support_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("help_support_button")
            .assertIsDisplayed()
    }

    @Test
    fun delete_account_nav_button_is_visible(){
        ScreenTestHelpers.launchPatientSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("delete_account_button")
            .assertIsDisplayed()
    }
}