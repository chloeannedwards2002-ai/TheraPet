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
 * Delete account confirmation screen test
 */

class DeleteAccountConfirmScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun delete_account_info_text_is_visible(){
        ScreenTestHelpers.launchDeleteAccountConfirmScreen(composeRule)

        composeRule
            .onNodeWithTag("info_text")
            .assertIsDisplayed()
    }

    @Test
    fun delete_account_button_is_visible(){
        ScreenTestHelpers.launchDeleteAccountConfirmScreen(composeRule)

        composeRule
            .onNodeWithTag("delete_account_confirm_button")
            .assertIsDisplayed()
    }

    @Test
    fun cancel__button_is_visible(){
        ScreenTestHelpers.launchDeleteAccountConfirmScreen(composeRule)

        composeRule
            .onNodeWithTag("cancel_button")
            .assertIsDisplayed()
    }
}