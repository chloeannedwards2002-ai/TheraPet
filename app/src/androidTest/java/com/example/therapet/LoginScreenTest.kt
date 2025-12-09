package com.example.therapet

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsRule
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<LoginActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    // Login_Screen_UI_Tests - Navigate back to the welcome page when the back arrow button is clicked
    @Test
    fun backButtonReturnsToMainActivity(){

        // Click the back arrow button
        composeTestRule
            .onNodeWithTag("back_button")
            .performClick()

        composeTestRule.waitForIdle()

        // Assert RegisterActivity is closed
        assert(composeTestRule.activity.isFinishing)
    }

    // Login_Screen_UI_Tests - Navigate to the registration page when register button is clicked
    @Test
    fun navigateToRegisterPageWhenRegisterButtonClicked(){
        // Click the register button
        composeTestRule
            .onNodeWithTag("register_button")
            .performClick()

        //Assert RegisterActivity is launched
        intended(hasComponent(RegisterActivity::class.java.name))
    }

    // Login_Screen_UI_Tests - Navigate to the home screen of TheraPet when the login button is clicked
    @Test
    fun navigateToHomeScreenWhenLoginButtonClicked(){
        // Click the login button
        composeTestRule
            .onNodeWithTag("login_button")
            .performClick()

        //Assert HomeScreenActivity is launched
        intended(hasComponent(HomeScreenActivity::class.java.name))
    }

    // Login_Screen_UI_Tests - Both input fields can be typed in
    @Test
    fun bothInputFieldsAreTypedIn(){
        // Type in user ID field
        composeTestRule
            .onNodeWithTag("user_login_id_input")
            .performTextInput("testID")

        // Type in password field
        composeTestRule
            .onNodeWithTag("login_password_input")
            .performTextInput("password")
    }

    // Login_Screen_UI_Tests - Toggle visibility of password
    @Test
    fun toggleVisibilityOfPassword(){
        composeTestRule.onNodeWithTag("login_password_input")
            .performTextInput("testpassword")

        // Reveal the password
        composeTestRule.onNodeWithTag("login_password_toggle")
            .performClick()

        // Hide it again
        composeTestRule.onNodeWithTag("login_password_toggle")
            .performClick()
    }

    // Login_Screen_UI_Tests - The remember password tick box is ticked and unticked
    @Test
    fun rememberPasswordCheckboxToggles() {
        val checkboxTag = "remember_password_tick_box"

        // Initially unchecked
        composeTestRule.onNodeWithTag(checkboxTag)
            .assertIsOff()

        // Click to check
        composeTestRule.onNodeWithTag(checkboxTag)
            .performClick()
            .assertIsOn()

        // Click to uncheck
        composeTestRule.onNodeWithTag(checkboxTag)
            .performClick()
            .assertIsOff()
    }


}