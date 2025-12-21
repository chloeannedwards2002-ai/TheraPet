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

    // Verify when clicking the "settings" button on the side out menu, the settings screen loads
    @Test
    fun onClickSettingsSideOutMenuNavigateToSettingsScreen() {
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
            .onNodeWithTag("drawer_settings_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("settings_screen")
            .assertIsDisplayed()
    }

    // Verify when clicking the + button on the home screen, the choose therapist screen loads
    @Test
    fun onClickPlusButtonHomeScreenNavigateToChooseTherapistScreen() {
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_screen")
            .assertIsDisplayed()

    }

    // Verify when clicking the continue button on the choose therapist screen, the book appointment screen loads
    @Test
    fun onClickContinueButtonNavigateToBookAppointmentScreen() {
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("continue_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("book_appointment_screen")
            .assertIsDisplayed()
    }

    // Verify when opening nav drawer and clicking profile button navigate to profile screen
    @Test
    fun onClickSideOutMenuProfileButtonNavigateToProfileScreen() {
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
            .onNodeWithTag("drawer_profile_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("profile_screen")
            .assertIsDisplayed()
    }

    // Verify when clicking edit password button on profile page, navigate to reset password screen
    @Test
    fun onClickEditPasswordProfileScreenNavigateToResetPassword(){
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

    // Verify that clicking on appointments button on nav bar navigates to appointments screen
    @Test
    fun onClickSideOutMenuAppointmentsButtonNavigateToAppointmentsScreen() {
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
            .onNodeWithTag("drawer_appointments_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_screen")
            .assertIsDisplayed()
    }

    // Verify that clicking appointments ("calendar") button on the home screen navigates to appointments screen
    @Test
    fun onClickHomeScreenAppointmentsButtonNavigateToAppointmentsScreen() {
        composeTestRule
            .onNodeWithTag("choose_login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_screen")
            .assertIsDisplayed()
    }

    // Verify the book appts button on appointments screen navigates to book appointments screen
    @Test
    fun onClickBookApptsButtonAppointmentsScreenNavToBookAppointmentsScreen(){
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

    // Verify the pet settings button on settings screen navigates to pet settings screen
    @Test
    fun onClickPetSettingsOnSettingsScreenNavigateToPetSettingsScreen(){
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

    // Verify that privacy poliy button on settings screen navigates to privacy policy screen
    @Test
    fun onClickPrivacyPolicyNavigateToPrivacyPolicyScreen(){
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

    // Verify help support button on settings screen goes to help support screen
    @Test
    fun onClickHelpSupportNavigateToHelpSupportScreen(){
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
