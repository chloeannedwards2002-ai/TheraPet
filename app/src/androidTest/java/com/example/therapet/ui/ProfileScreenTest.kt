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
 * Privacy Policy screen test
 */

class ProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun profile_avatar_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("profile_avatar")
            .assertIsDisplayed()
    }

    @Test
    fun first_name_field_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("edit_first_name_field")
            .assertIsDisplayed()
    }
    @Test
    fun edit_first_name_button_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("first_name_edit_button")
            .assertIsDisplayed()
    }

    @Test
    fun surname_field_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("edit_surname_field")
            .assertIsDisplayed()
    }

    @Test
    fun edit_surname_button_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("surname_edit_button")
            .assertIsDisplayed()
    }

    @Test
    fun patient_id_field_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("patient_id_field")
            .assertIsDisplayed()
    }

    @Test
    fun mobile_field_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("edit_mobile_field")
            .assertIsDisplayed()
    }

    @Test
    fun edit_mobile_button_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("mobile_edit_button")
            .assertIsDisplayed()
    }

    @Test
    fun edit_password_field_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("reset_password_button")
            .assertIsDisplayed()
    }

    @Test
    fun edit_password_button_is_visible(){
        ScreenTestHelpers.launchProfileScreen(composeRule)

        composeRule
            .onNodeWithTag("reset_password_button")
            .assertIsDisplayed()
    }

}