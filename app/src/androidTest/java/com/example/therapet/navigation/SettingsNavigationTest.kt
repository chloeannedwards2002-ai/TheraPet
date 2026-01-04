package com.example.therapet.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.BaseNav
import org.junit.Test
import com.example.therapet.helpers.login
import com.example.therapet.helpers.navDrawer

/**
 * @Author: Chloe Edwards
 * @Date: 22/12/2025
 *
 * These are settings navigation tests. Not all settings pages are implemented yet.
 * TODO: Finish implementing settings screens
 *
 * Settings navigation tests:
 *  1. Settings -> Profile
 *  2. Settings -> Profile -> Reset password
 *  3. Settings -> Pet settings
 *  4. Settings -> Privacy Policy
 *  5. Settings -> Help & Support
 */

class SettingsNavigationTest : BaseNav() {

    // 1. Settings -> Profile
    @Test
    fun onClickProfileButtonNavDrawer(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_profile_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("profile_screen")
            .assertIsDisplayed()
    }

    // 2. Settings -> Profile -> Reset password
    @Test
    fun onClickEditPasswordProfileScreenNavigateToResetPassword(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_profile_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("profile_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("reset_password_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("reset_password_screen")
            .assertIsDisplayed()


    }

    // 3. Settings -> Pet settings
    @Test
    fun onClickPetSettingsOnSettingsScreenNavigateToPetSettingsScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("pet_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("pet_settings_screen")
            .assertIsDisplayed()
    }

    // 4. Settings -> Privacy Policy
    @Test
    fun onClickPrivacyPolicyNavigateToPrivacyPolicyScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("privacy_policy_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("privacy_policy_screen")
            .assertIsDisplayed()
    }

    // 5. Settings -> Help & Support
    @Test
    fun onClickHelpSupportNavigateToHelpSupportScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("help_support_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("help_support_screen")
            .assertIsDisplayed()
    }
}