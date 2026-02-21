package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 13/01/2026
 *
 * Appointments screen test
 */

class TherapistAppointmentsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun therapist_add_appointment_button_is_visible() {
        ScreenTestHelpers.launchTherapistAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_appointment_button")
            .assertIsDisplayed()
            .assertTextEquals("+ Add appointment")
    }

    @Test
    fun patient_book_appointment_button_is_not_visible() {
        ScreenTestHelpers.launchPatientAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_appointment_button")
            .assertDoesNotExist()
    }

    @Test
    fun clicking_appointment_opens_edit_dialog() {
        ScreenTestHelpers.launchTherapistAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("appointment_cell")
            .performClick()

        composeRule
            .onNodeWithText("Appointment")
            .assertIsDisplayed()
    }

    @Test
    fun patient_cannot_open_date_picker() {
        ScreenTestHelpers.launchPatientAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_appointment_button")
            .assertDoesNotExist()
    }

    @Test
    fun edit_time_opens_date_picker() {
        ScreenTestHelpers.launchTherapistAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("appointment_cell")
            .performClick()

        composeRule
            .onNodeWithTag("edit_time_button")
            .performClick()

        composeRule
            .onNodeWithTag("date_time_picker", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}