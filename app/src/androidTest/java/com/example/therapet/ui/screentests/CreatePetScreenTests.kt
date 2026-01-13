package com.example.therapet.ui.screentests

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.therapet.helpers.ScreenTestHelpers
import org.junit.Rule
import org.junit.Test

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Pet screen UI tests
 */

class CreatePetScreenTests {
    @get: Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun create_pet_top_bar_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("create_pet_top_bar")
            .assertIsDisplayed()
    }

    @Test
    fun pet_penguin_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_penguin")
            .assertIsDisplayed()
    }

    @Test
    fun choose_colour_text_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("choose_colour_text")
            .assertIsDisplayed()
    }

    @Test
    fun choose_colour_carousel_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("colour_carousel")
            .assertIsDisplayed()
    }

    @Test
    fun choose_pet_name_text_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("choose_name_text")
            .assertIsDisplayed()
    }

    @Test
    fun pet_name_input_field_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_name_input")
            .assertIsDisplayed()
    }

    @Test
    fun reset_button_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("reset_button")
            .assertIsDisplayed()
    }

    @Test
    fun confirm_button_is_visible(){
        ScreenTestHelpers.launchCreatePetScreen(composeRule)

        composeRule
            .onNodeWithTag("pet_confirm_button")
            .assertIsDisplayed()
    }




}