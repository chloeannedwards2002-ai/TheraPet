package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 14/01/2026
 *
 * Home screen test
 */

class HomeScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun choose_therapist_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("choose_therapist_button")
            .assertIsDisplayed()
    }

    @Test
    fun pet_care_bar_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_care_bar")
            .assertIsDisplayed()
    }

    @Test
    fun water_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("water_button")
            .assertIsDisplayed()
    }

    @Test
    fun food_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("food_button")
            .assertIsDisplayed()
    }

    @Test
    fun sleep_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("sleep_button")
            .assertIsDisplayed()
    }

    @Test
    fun pet_penguin_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_penguin")
            .assertIsDisplayed()
    }

    @Test
    fun drawer_is_clickable(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("nav_drawer")
            .assertIsDisplayed()
    }

    @Test
    fun profile_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_profile_button")
    }

    @Test
    fun appointments_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_appointments_button")
    }

    @Test
    fun notifications_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_notifications_button")
    }

    @Test
    fun settings_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_settings_button")
    }

    @Test
    fun logout_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_logout_button")
    }

}