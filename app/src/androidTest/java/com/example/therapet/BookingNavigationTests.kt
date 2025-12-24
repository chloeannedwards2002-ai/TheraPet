package com.example.therapet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.helpers.BaseNav
import com.example.therapet.helpers.login
import org.junit.Test

/**
 * @Author: Chloe Edwards
 * @Date: 22/12/2025
 *
 * These are booking navigation tests. There is no booking functionality yet.
 * TODO: Implement booking
 *
 * Booking navigation tests:
 *  1. Home screen -> Choose therapist screen
 *  2. Home screen -> Choose therapist screen -> book appointment screen
 *  3. Home screen -> Appointments screen
 */

class BookingNavigationTests : BaseNav() {

    //1. Home screen -> Choose therapist screen
    @Test
    fun onClickPlusButtonHomeScreenNavigateToChooseTherapistScreen() {
        composeTestRule.login()

        composeTestRule
            .onNodeWithTag("choose_therapist_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_screen")
            .assertIsDisplayed()

    }

    // 2. Home screen -> Choose therapist screen -> book appointment screen
    @Test
    fun onClickContinueButtonNavigateToBookAppointmentScreen() {
        composeTestRule.login()

        composeTestRule
            .onNodeWithTag("choose_therapist_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("choose_therapist_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("continue_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("book_appointment_screen")
            .assertIsDisplayed()
    }

    // 3. Home screen -> Appointments screen
    @Test
    fun onClickHomeScreenAppointmentsButtonNavigateToAppointmentsScreen() {
        composeTestRule.login()

        composeTestRule
            .onNodeWithTag("appointments_button")
            .assertIsDisplayed()
            .performClick()

        composeTestRule
            .onNodeWithTag("appointments_screen")
            .assertIsDisplayed()
    }
}





