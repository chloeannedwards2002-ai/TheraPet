package com.example.therapet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.BaseNav
import com.example.therapet.helpers.login
import com.example.therapet.helpers.navDrawer
import org.junit.Test

/**
 * @Author: Chloe Edwards
 * @Date: 22/12/2025
 *
 * These are nav menu navigation tests.
 *
 * Authorisation navigation tests:
 *  1. Verify clicking the nav menu icon (burger menu), the nav drawer opens
 *  2. Nav drawer -> Settings screen
 *  3. Nav drawer -> Profile screen
 *  4. Nav drawer -> Notifications screen
 *  5. Nav drawer -> Appointments screen
 *  6. Nav Drawer -> Appointments screen -> Book appointments screen
 *  7. Nav drawer -> Logout
 */

class NavMenuNavigationTests : BaseNav() {

    // 1. Verify clicking the nav menu icon (burger menu), the nav drawer opens
    @Test
    fun onClickNavMenuIconNavMenuAppears(){
        composeTestRule.login()

        composeTestRule
            .onNodeWithTag("menu_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("nav_drawer")
            .assertIsDisplayed()
    }

    // 2. Nav drawer -> Settings screen
    @Test
    fun onClickSettingsSideOutMenuNavigateToSettingsScreen() {
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()
    }

    // 3. Nav drawer -> Profile screen
    @Test
    fun onClickSideOutMenuProfileButtonNavigateToProfileScreen() {
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_profile_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("profile_screen")
            .assertIsDisplayed()
    }

    // 4. Nav drawer -> Notifications screen

    // 5. Nav drawer -> Appointments screen
    @Test
    fun onClickSideOutMenuAppointmentsButtonNavigateToAppointmentsScreen() {
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_appointments_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_screen")
            .assertIsDisplayed()
    }

    // 6. Nav Drawer -> Appointments screen -> Book appointments screen
    @Test
    fun onClickBookApptsButtonAppointmentsScreenNavToBookAppointmentsScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_appointments_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("book_appointment_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("book_appointment_screen")
            .assertIsDisplayed()
    }

    // 7. Nav drawer -> Logout
    @Test
    fun onClickLogoutSideOutMenuReturnToWelcomeScreen(){
        composeTestRule.login()
        composeTestRule.navDrawer()

        composeTestRule
            .onNodeWithTag("drawer_logout_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("welcome_screen")
            .assertIsDisplayed()
    }
}