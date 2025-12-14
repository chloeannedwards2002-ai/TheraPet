package com.example.therapet

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.therapet.nav.TheraPet
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            TheraPet(navController)
        }
    }

    // Verify the start destination is "welcome"
    @Test
    fun navHostVerifyStartDestination() {
        composeTestRule
            .onNodeWithTag("welcome_screen")
            .assertIsDisplayed()
    }

    /* Verify when clicking the back button on the top bar, the previous screen on the stack is displayed
    (also doubles as a verification of the login button navigating to the login screen) */
    @Test
    fun navToLoginThenNavBackToWelcome(){
        // Click "Login" button on the Welcome screen
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .assertIsDisplayed()
            .performClick()

        // Verify the login screen is loaded
        composeTestRule
            .onNodeWithTag("login_screen")
            .assertIsDisplayed()

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

    // Verify that the login button on the Welcome screen navigates to the login page
    @Test
    fun onClickRegisterButtonWelcomePageNavigatesToRegisterScreen(){
        // Click "Register" button on the Welcome Screen
        composeTestRule
            .onNodeWithTag("choose_register_button")
            .assertIsDisplayed()
            .performClick()

        // Verify the register screen is loaded
        composeTestRule
            .onNodeWithTag("register_screen")
            .assertIsDisplayed()
    }

    // Verify that the register button on the Register screen navigates to the Create Pet screen
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

    // Verify that the login button on the Login screen navigates to the home screen
    @Test
    fun onClickLoginButtonLoginScreenNavigatesToHomeScreen() {
        // Navigate to the create pet screen
        composeTestRule
            .onNodeWithTag(testTag = "choose_login_button")
            .assertIsDisplayed()
            .performClick()

        // Click the "Login" button on the Login screen
        composeTestRule
            .onNodeWithTag(testTag = "login_button")
            .assertIsDisplayed()
            .performClick()

        // Verify the home screen is loaded
        composeTestRule
            .onNodeWithTag(testTag = "home_screen")
            .assertIsDisplayed()
    }

    // Verify that the confirm button on the create pet screen navigates to the home screen
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

    // Verify the navigation menu opens when the nav icon is clicked
    @Test
    fun sideOutMenuOpensWhenMenuButtonClick(){
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("home_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("menu_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("nav_drawer")
            .assertIsDisplayed()
    }

    // Verify when the user clicks the logout button from the side out menu, return to the welcome screen (TODO: Change to test login/logout state when implemented)
    @Test
    fun onClickLogoutSideOutMenuReturnToWelcomeScreen(){
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("menu_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("drawer_logout_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("welcome_screen")
            .assertIsDisplayed()
    }
}