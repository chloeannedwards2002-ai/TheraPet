package com.example.therapet.ui

import androidx.activity.ComponentActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.semantics.SemanticsProperties.ProgressBarRangeInfo
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.HomeNavigationDrawer
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.helpers.ScreenTestHelpers
import com.example.therapet.helpers.createTestUser
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 14/01/2026
 *
 * Home screen test
 */

class HomeScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun pet_care_bar_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_care_bar")
            .assertIsDisplayed()
    }

    @Test
    fun water_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("water_button")
            .assertIsDisplayed()
    }

    @Test
    fun food_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("food_button")
            .assertIsDisplayed()
    }

    @Test
    fun sleep_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("sleep_button")
            .assertIsDisplayed()
    }

    @Test
    fun pet_penguin_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_penguin")
            .assertIsDisplayed()
    }

    @Test
    fun drawer_is_clickable(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("nav_drawer")
            .assertIsDisplayed()
    }

    @Test
    fun profile_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_profile_button")
            .assertIsDisplayed()
    }

    @Test
    fun appointments_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_appointments_button")
            .assertIsDisplayed()
    }

    @Test
    fun notifications_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_notifications_button")
            .assertIsDisplayed()
    }

    @Test
    fun settings_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_settings_button")
            .assertIsDisplayed()
    }

    @Test
    fun logout_button_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_logout_button")
            .assertIsDisplayed()
    }

    @Test
    fun full_name_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("full_name")
            .assertIsDisplayed()
    }

    @Test
    fun drawer_profile_avatar_is_visible(){
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        composeRule
            .onNodeWithTag("menu_button")
            .performClick()

        composeRule
            .onNodeWithTag("drawer_profile_avatar")
            .assertIsDisplayed()
    }

    // Testing the name not just the full name text
    @Test
    fun drawer_header_shows_full_avatar_and_full_name() {
        val fakeUser = createTestUser(
            userid = "123456789012",
            firstname = "Jane",
            surname = "Doe",
            password = "Password_123",
            role = UserRole.PATIENT
        )

        composeRule.setContent {
            TheraPetTheme {
                HomeNavigationDrawer(
                    drawerState = rememberDrawerState(DrawerValue.Open),
                    user = fakeUser,
                    onDestinationClicked = {}
                ) {

                }
            }
        }

        composeRule.onNodeWithTag("drawer_profile_avatar")
            .assertExists()

        composeRule.onNodeWithTag("full_name")
            .assertTextEquals("Jane Doe")
    }

    @Test
    fun food_button_increases_food_bar() {
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        // Get initial value
        val before = composeRule
            .onNodeWithTag("food_need_bar")
            .fetchSemanticsNode()
            .config
            .getOrNull(ProgressBarRangeInfo)
            ?.current ?: 0f

        // Click food button
        composeRule
            .onNodeWithTag("food_button")
            .performClick()

        composeRule.waitForIdle()

        val after = composeRule
            .onNodeWithTag("food_need_bar")
            .fetchSemanticsNode()
            .config
            .getOrNull(ProgressBarRangeInfo)
            ?.current ?: 0f

        assert(after > before)
    }

    @Test
    fun water_button_increases_water_bar() {
        ScreenTestHelpers.launchPatientHomeScreen(composeRule)

        val before = composeRule
            .onNodeWithTag("water_need_bar")
            .fetchSemanticsNode()
            .config
            .getOrNull(ProgressBarRangeInfo)
            ?.current ?: 0f

        composeRule
            .onNodeWithTag("water_button")
            .performClick()

        composeRule.waitForIdle()

        val after = composeRule
            .onNodeWithTag("water_need_bar")
            .fetchSemanticsNode()
            .config
            .getOrNull(ProgressBarRangeInfo)
            ?.current ?: 0f

        assert(after > before)
    }
}