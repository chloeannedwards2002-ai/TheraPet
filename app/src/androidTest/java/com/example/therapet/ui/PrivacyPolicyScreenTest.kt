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
 * @date: 14/01/2026
 *
 * Privacy Policy screen test
 */

class PrivacyPolicyScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun privacy_policy_text_is_visible(){
        ScreenTestHelpers.launchPrivacyPolicyScreen(composeRule)

        composeRule
            .onNodeWithTag("privacy_policy_text")
            .assertIsDisplayed()
    }
}


