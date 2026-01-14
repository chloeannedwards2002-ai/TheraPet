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
 * Appointments screen test
 */

class AppointmentsScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun book_appointment_button_is_visible(){
        ScreenTestHelpers.launchAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_appointment_button")
            .assertIsDisplayed()
    }
}