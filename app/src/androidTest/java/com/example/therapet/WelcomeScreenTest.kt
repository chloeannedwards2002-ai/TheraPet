package com.example.therapet

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsRule
import org.junit.Rule
import org.junit.Test


class WelcomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    //When the user clicks on the "Register" button on the welcome page, the app should navigate to the Registration screen
    @Test
    fun navigateFromWelcomeToRegister()
    {
        // Click the Register button
        composeTestRule
            .onNodeWithTag("choose_register_button")
            .performClick()

        // Assert RegisterActivity is launched
        intended(hasComponent(RegisterActivity::class.java.name))
    }

   //When the user clicks on the "Login" button on the welcome page, the app should navigate to the Login screen
    @Test
    fun navigateFromWelcomeToLogin()
    {
        // Click the Login button
        composeTestRule
            .onNodeWithTag(testTag = "choose_login_button")
            .performClick()

        //Assert LoginActivity is launched
        intended(hasComponent(LoginActivity::class.java.name))
    }
}