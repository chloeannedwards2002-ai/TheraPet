package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.screens.WelcomeScreen
import com.example.therapet.app.ui.screens.appts.AppointmentsScreen
import com.example.therapet.app.ui.screens.booking.BookAppointmentScreen
import com.example.therapet.app.ui.screens.booking.ChooseTherapistScreen
import com.example.therapet.app.ui.screens.home.HomeScreen
import com.example.therapet.app.ui.screens.login.LoginScreen
import com.example.therapet.app.ui.screens.pet.CreatePetScreen
import com.example.therapet.app.ui.screens.pet.PetSettingsScreen
import com.example.therapet.app.ui.screens.register.RegisterScreen
import com.example.therapet.app.ui.screens.settings.accountmanagement.DeleteAccountConfirmScreen
import com.example.therapet.app.ui.screens.settings.accountmanagement.DeleteAccountScreen
import com.example.therapet.app.ui.screens.settings.HelpSupportScreen
import com.example.therapet.app.ui.screens.settings.PrivacyPolicyScreen
import com.example.therapet.app.ui.screens.settings.SettingsScreen
import com.example.therapet.app.ui.screens.settings.accountmanagement.profile.ProfileScreen
import com.example.therapet.app.ui.screens.settings.accountmanagement.ResetPasswordScreen
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * These are helpers for screen UI tests e.g. launching screens
 */

object ScreenTestHelpers {

    // fake users for testing
    private val testPatientUser = UserEntity(
        userid = "123456789123",
        firstname = "Bob",
        surname = "Bobbington",
        password = "Password_123",
        role = UserRole.PATIENT
    )

    private val testTherapistUser = UserEntity(
        userid = "1231231231231234",
        firstname = "Jane",
        surname = "Doe",
        password = "Password_123",
        role = UserRole.THERAPIST
    )

    fun launchWelcomeScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                WelcomeScreen(
                    onRegisterNav = {},
                    onLoginNav = {}
                )
            }
        }
    }

    fun launchLoginScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            LoginScreen(
                onLogin = {_, _ ->},
                onRegisterNav = {},
                onBack = {},
                errorMessage = null
            )
        }
    }

    //register screen
    fun launchRegisterScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ) {
        composeRule.setContent {
            TheraPetTheme {
                RegisterScreen(
                    onRegister = { _, _, _, _ -> },
                    onBack = {}
                )
            }
        }
    }

    //settings screen (patient state)
    fun launchPatientSettingsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            TheraPetTheme {
                SettingsScreen(
                    role = UserRole.PATIENT,
                    onBack = {},
                    onPetSettings = {},
                    onPrivacyPolicy = {},
                    onHelpSupport = {},
                    onDeleteAccount = {}
                )
            }
        }
    }

    //settings screen (therapist state)
    fun launchTherapistSettingsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            TheraPetTheme {
                SettingsScreen(
                    role = UserRole.THERAPIST,
                    onBack = {},
                    onPetSettings = {},
                    onPrivacyPolicy = {},
                    onHelpSupport = {},
                    onDeleteAccount = {}
                )
            }
        }
    }

    //create pet screen
    fun launchCreatePetScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent {
            TheraPetTheme {
                CreatePetScreen(
                    selectedColourIndex = 0,
                    onColourSelected = {},
                    onCreatePet = {}
                )
            }
        }
    }

    //homescreen
    fun launchPatientHomeScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>,
        role: UserRole = UserRole.PATIENT,
        petColourIndex: Int = 0,
        user: UserEntity = testPatientUser
    ){
        composeRule.setContent {
            TheraPetTheme {
                HomeScreen(
                    role = role,
                    petColourIndex = petColourIndex,
                    onLogout = {},
                    onSettings = {},
                    onNotifs = {},
                    onAppts = {},
                    onBookAppt = {},
                    onProfile = {},
                    user = user
                )
            }
        }
    }

    fun launchPetSettingsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                PetSettingsScreen(
                    onBack = {}
                )
            }
        }
    }

    fun launchTherapistHomeScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>,
        role: UserRole = UserRole.THERAPIST,
        petColourIndex: Int = 0,
        user: UserEntity = testTherapistUser
    ){
        composeRule.setContent {
            TheraPetTheme {
                HomeScreen(
                    role = role,
                    petColourIndex = petColourIndex,
                    onLogout = {},
                    onSettings = {},
                    onNotifs = {},
                    onAppts = {},
                    onBookAppt = {},
                    onProfile = {},
                    user = user
                )
            }
        }
    }

    fun launchAppointmentsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                AppointmentsScreen(
                    onBack = {},
                    onBookAppt = {}
                )
            }
        }
    }

    fun launchBookAppointmentsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    )
    {
        composeRule.setContent{
            TheraPetTheme{
                BookAppointmentScreen(
                    onBack ={},
                    onBook ={}
                )
            }
        }
    }

    fun launchChooseTherapistScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                ChooseTherapistScreen(
                    onBack = {},
                    onContinue = {}
                )
            }
        }
    }

    fun launchDeleteAccountConfirmScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                DeleteAccountConfirmScreen(
                    onBack = {},
                    onDeleteAccount = {}
                )
            }
        }
    }

    fun launchDeleteAccountScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                DeleteAccountScreen(
                    onBack = {},
                    onContinue = {},
                    validatePassword = { _ -> true }
                )
            }
        }
    }

    fun launchHelpSupportScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                HelpSupportScreen(
                    onBack = {}
                )
            }
        }
    }

    fun launchPrivacyPolicyScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                PrivacyPolicyScreen(
                    onBack = {},
                )
            }
        }
    }

    fun launchProfileScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                ProfileScreen(
                    onBack = {},
                    onEditPassword = {},
                    firstName = "Jane",
                    surname = "Doe",
                    userId = "123456789012",
                    mobile = null
                )
            }
        }
    }

    fun launchResetPasswordScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                ResetPasswordScreen(
                    onBack ={},
                    onResetPassword={}
                )
            }
        }
    }
}