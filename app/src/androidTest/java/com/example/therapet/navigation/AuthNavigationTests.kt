package com.example.therapet.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.BaseNav
import org.junit.Test

/**
 * @Author: Chloe Edwards
 * @Date: 21/12/2025
 *
 * These are authorisation navigation tests. There is no authorisation functionality yet these are just plain nav tests for now.
 * TODO: Implement authorisation (login / register / create pet)
 *
 * Authorisation navigation tests:
 *  1. Verify start destination as "Home Screen"
 *  2. Welcome -> Login
 *  3. Welcome -> Register
 *  4. Welcome -> Login -> Home
 *  5. Welcome -> Register -> Create Pet
 *  6. Welcome -> Register -> Create Pet -> Home
 *  7. Login screen -> Welcome Screen (this tests the back button on the top bar which is used across multiple screens)
 */

class AuthNavigationTests : BaseNav() {

    // 1. Verify start destination as "Home Screen"
    @Test
    fun navHostVerifyStartDestination() {
        composeTestRule
            .onNodeWithTag("welcome_screen")
            .assertIsDisplayed()
    }

    // 2. Welcome -> Login
    @Test
    fun onClickRegisterNavButtonNavToRegisterScreen(){
        composeTestRule
            .onNodeWithTag("choose_register_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("register_screen")
            .assertIsDisplayed()
    }

    // 3. Welcome -> Register
    @Test
    fun onClickLoginNavButtonNavToLoginScreen(){
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("login_screen")
            .assertIsDisplayed()
    }

    // 4. Welcome -> Login -> Home
    @Test
    fun onClickLoginButtonNavToHomeScreen(){
        composeTestRule
            .onNodeWithTag(testTag = "choose_login_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = "login_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = "home_screen")
            .assertIsDisplayed()
    }

    // 5. Welcome -> Register -> Create Pet
    @Test
    fun onClickRegisterButtonRegisterScreenNavigatesToCreatePetScreen(){
        composeTestRule
            .onNodeWithTag("choose_register_button")
            .assertIsDisplayed()
            .performClick()

        // Click "Register" button on the Register screen
        composeTestRule
            .onNodeWithTag("register_button")
            .assertIsDisplayed()
            .performClick()

        // Verify the register screen is loaded
        composeTestRule
            .onNodeWithTag("create_pet_screen")
            .assertIsDisplayed()
    }

    // 6. Welcome -> Register -> Create Pet -> Home
    @Test
    fun onClickConfirmButtonRegisterScreenNavigatesToHomeScreen() {
        // Navigate to the create pet screen
        composeTestRule
            .onNodeWithTag(testTag = "choose_register_button")
            .assertIsDisplayed()
            .performClick()

        // Click register button
        composeTestRule
            .onNodeWithTag(testTag = "register_button")
            .assertIsDisplayed()
            .performClick()

        // Click the "Confirm" button
        composeTestRule
            .onNodeWithTag(testTag = "pet_confirm_button")
            .assertIsDisplayed()
            .performClick()

        // Verify the home screen is loaded
        composeTestRule
            .onNodeWithTag(testTag = "home_screen")
            .assertIsDisplayed()
    }

    // 7. Login screen -> Welcome Screen (this tests the back button on the top bar which is used across multiple screens)
    @Test
    fun navToLoginThenNavBackToWelcome(){
        // Login
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .assertIsDisplayed()
            .performClick()

        // Click the back button on the login screen
        composeTestRule
            .onNodeWithTag("back_button")
            .assertIsDisplayed()
            .performClick()

        // Verify that the previous screen (in this case welcome) is loaded
        composeTestRule
            .onNodeWithTag("welcome_screen")
            .assertIsDisplayed()
    }
}





