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
 * Booking appointments screen test
 */

class BookAppointmentScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

   /* @Test
    fun book_button_is_visible(){
        ScreenTestHelpers.launchBookAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_button")
            .assertIsDisplayed()
    }*/

}