package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
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
    fun therapist_role_doesnt_shows_pet_settings_button(){
        ScreenTestHelpers.launchTherapistSettingsScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_settings_button")
            .assertDoesNotExist()
    }
}