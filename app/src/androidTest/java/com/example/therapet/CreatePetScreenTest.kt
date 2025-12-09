package com.example.therapet

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsRule
import com.example.therapet.ui.theme.customs.isSelectedCircleKey
import org.junit.Rule
import org.junit.Test

class CreatePetScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<CreatePetActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    // Create_Pet_Page_Tests - The pet name input field allows typing
    @Test
    fun typeIntoPetNameInputField(){
        // Assert that the box exists
        composeTestRule.onNodeWithTag("pet_name_input").assertExists()

        composeTestRule
            .onNodeWithTag("pet_name_input")
            .performTextInput("bob")
    }

    // Create_Pet_Page_Tests - The reset button resets the pet name input field and the colour of the pet back to the default colour
    @Test
    fun resetColourChoiceAndPetNameInput(){
        // Click the right carousel button
        composeTestRule.onNodeWithTag("carousel_next")
            .assert(hasClickAction())
            .performClick()

        // Verify that the selectedIndex circle is now highlighted
        composeTestRule.onNodeWithTag("circle_1").assertExists()

        // Type a name into the pet name input
        composeTestRule.onNodeWithTag("pet_name_input")
            .performTextInput("Bob")

        // Click the reset button
        composeTestRule.onNodeWithTag("reset_button")
            .performClick()

        // Verify that the pet name is cleared
        composeTestRule.onNodeWithTag("pet_name_input")
            .assertExists()
            .assertTextContains("", substring = true)

        // Verify the carousel has returned to the first index (circle_0)
        composeTestRule.onNodeWithTag("circle_0").assertExists()
    }

    private fun selectedCircleMatch(index: Int) =
        SemanticsMatcher.expectValue(isSelectedCircleKey, true)
            .and(hasTestTag("circle_$index"))

    /* Create_Pet_Page_Tests - When clicking the arrows, the carousel scrolls through different colours
        a. Select next circle text
     */
    @Test
    fun clickingNextArrowSelectsNextCircle(){
        // Initially, circle with index 0 is selected
        composeTestRule.onNode(selectedCircleMatch(0)).assertExists()

        //Click the next button
        composeTestRule.onNodeWithTag("carousel_next")
            .assert(hasClickAction())
            .performClick()

        // Check circle of index 1 is selected
        composeTestRule.onNode(selectedCircleMatch(1)).assertExists()
    }

    /* Create_Pet_Page_Tests - When clicking the arrows, the carousel scrolls through different colours
     b. Select prev circle text
  */
    @Test
    fun clickingPreviousArrowSelectsPreviousCircle(){
        // Click next button first
        composeTestRule.onNodeWithTag("carousel_next").performClick()

        // Verify circle of index 1 is selected
        composeTestRule.onNode(selectedCircleMatch(1)).assertExists()

        // Click the previous button
        composeTestRule.onNodeWithTag("carousel_prev")
            .assert(hasClickAction())
            .performClick()

        // Verify circle of index 0 is selected
        composeTestRule.onNode(selectedCircleMatch(0)).assertExists()
    }


    // Create_Pet_Page_Tests - Confirm button navigates to the TheraPet home page
    @Test
    fun navigateFromCreatePetToHomeScreen(){
        // Click the Confirm button
        composeTestRule
            .onNodeWithTag("confirm_button")
            .performClick()

        // Assert RegisterActivity is launched
        intended(hasComponent(HomeScreenActivity::class.java.name))
    }
}