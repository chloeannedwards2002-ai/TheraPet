package com.example.therapet.helpers

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.therapet.app.nav.NavGraph
import org.junit.Before
import org.junit.Rule

/**
 * @Author: Chloe Edwards
 * @Date: 22/12/2025
 *
 * This file is a shared setup class that automatically launches the NavGraph before each test
 * so all tests start at the same clean state without repeating the set up code
 */

abstract class BaseNav {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(navController)
        }
    }
}