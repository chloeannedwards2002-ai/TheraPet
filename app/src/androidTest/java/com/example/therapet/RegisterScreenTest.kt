package com.example.therapet

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.rule.IntentsRule
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RegisterActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    // When clicking the back button, return to the welcome screen
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

    //Registration_Screen_UI_Tests - The user can type in all 5 input boxes
    @Test
    fun typeIntoAllRegistrationInputFields(){
        // Assert that the boxes exist in the boxes
        composeTestRule.onNodeWithTag("user_id_input").assertExists()
        composeTestRule.onNodeWithTag("first_name_input").assertExists()
        composeTestRule.onNodeWithTag("surname_input").assertExists()
        composeTestRule.onNodeWithTag("password_input").assertExists()
        composeTestRule.onNodeWithTag("confirm_password_input").assertExists()

        // Type in User ID input field
        composeTestRule
            .onNodeWithTag("user_id_input")
            .performTextInput("test")

        // Type in first name input field
        composeTestRule
            .onNodeWithTag("first_name_input")
            .performTextInput("test")

        // Type in surname input field
        composeTestRule
            .onNodeWithTag("surname_input")
            .performTextInput("test")

        // Type in password input field
        composeTestRule
            .onNodeWithTag(testTag = "password_input")
            .performTextInput("test")

        // Type in confirm password input field
        composeTestRule
            .onNodeWithTag("confirm_password_input")
            .performTextInput("test")
    }

    // Registration_Screen_UI_Tests - Toggle visibility of password
    @Test
    fun passwordVisibleToggleShowsAndHidesText(){
        // --- Password box ----
        composeTestRule.onNodeWithTag("password_input")
            .performTextInput("testpassword")

        // Reveal the password
        composeTestRule.onNodeWithTag("password_toggle")
            .performClick()

        // Hide it again
        composeTestRule.onNodeWithTag("password_toggle")
            .performClick()

        // --- Confirm password box ---
        composeTestRule.onNodeWithTag("confirm_password_input")
            .performTextInput("testpassword")

        // Reveal the confirmed password
        composeTestRule.onNodeWithTag("confirm_password_toggle")
            .performClick()

        // Hide it again
        composeTestRule.onNodeWithTag("confirm_password_toggle")
            .performClick()
    }

}

