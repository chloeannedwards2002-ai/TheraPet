package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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
 * Booking appointments screen test
 */

class PatientAppointmentScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun patient_step1_book_button_is_visible() {
        ScreenTestHelpers.launchPatientAppointmentsScreen(composeRule)

        composeRule
            .onNodeWithTag("book_button")
            .assertIsDisplayed()
    }

    @Test
    fun patient_step2_book_button_is_not_visible() {
        ScreenTestHelpers.launchChooseTherapistStep(composeRule)

        composeRule
            .onNodeWithTag("book_button")
            .assertDoesNotExist()
    }

    @Test
    fun therapist_list_is_displayed_in_step2() {
        ScreenTestHelpers.launchChooseTherapistStep(composeRule)

        composeRule
            .onNodeWithText("Bob Bobbington")
            .assertIsDisplayed()
    }

    @Test
    fun patient_step3_book_button_is_not_visible() {
        ScreenTestHelpers.launchBookAppointmentStep(composeRule)

        composeRule
            .onNodeWithTag("book_button")
            .assertDoesNotExist()
    }

    @Test
    fun shows_empty_state_when_no_appointments() {
        ScreenTestHelpers.launchBookAppointmentStepEmpty(composeRule)

        composeRule
            .onNodeWithText("No appointments available")
            .assertIsDisplayed()
    }
}