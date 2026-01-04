package com.example.therapet.helpers

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

/**
 * @Author: Chloe Edwards
 * @Date: 22/12/2025
 *
 * This file holds test helpers for TheraPet tests.
 * Some tests require duplicated steps such as logging in so instead of repeating those steps over
 *  and over, this file provides helpers that can be called to do those steps instead.
 */

// Login helper - All nav tests start at the home screen so this helper speeds up the login step
fun ComposeTestRule.login(){
    onNodeWithTag("choose_login_button")
        .assertIsDisplayed()
        .performClick()

    onNodeWithTag("login_button")
        .assertIsDisplayed()
        .performClick()

    onNodeWithTag("home_screen")
        .assertIsDisplayed()
}

//  Nav drawer helper - Used for most nav drawer tests where they include logging in and opening the nav drawer
fun ComposeTestRule.navDrawer(){
    onNodeWithTag("menu_button")
        .assertIsDisplayed()
        .performClick()


    onNodeWithTag("nav_drawer")
        .assertIsDisplayed()
}


