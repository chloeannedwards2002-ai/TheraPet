package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 13/01/2026
 *
 * Choose therapist screen test
 */

class ChooseTherapistScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun choose_therapist_continue_button_is_visible(){
        ScreenTestHelpers.launchChooseTherapistScreen(composeRule)

        composeRule
            .onNodeWithTag("continue_button")
            .assertIsDisplayed()
    }
}