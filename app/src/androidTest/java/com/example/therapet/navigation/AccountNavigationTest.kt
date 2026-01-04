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
 * These are account navigation tests. There is no delete account functionality yet these are just plain nav tests for now.
 *
 * Authorisation navigation tests:
 *  1. Welcome screen -> Delete account screen
 *  2. Welcome screen -> Delete account confirmation screen
 */

class AccountNavigationTest : BaseNav() {
    // Verify delete account button navigates to delete account screen
    @Test
    fun onClickDeleteAccountNavigateToDeleteAccountScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("delete_account_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("delete_account_screen")
            .assertIsDisplayed()
    }

    // Verify continue button on delete acc screen navigates to the confirm deletion screen
    @Test
    fun onClickContinueNavigateToDeleteAccountConfirmationScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("delete_account_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("delete_account_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("delete_account_confirm_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("delete_account_confirm_screen")
            .assertIsDisplayed()
    }

}