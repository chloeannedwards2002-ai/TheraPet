package com.example.therapet.helpers

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.AppointmentType
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.screens.WelcomeScreen
import com.example.therapet.app.ui.screens.appts.AppointmentsScreen
import com.example.therapet.app.ui.screens.booking.BookingStep
import com.example.therapet.app.ui.screens.booking.PatientAppointmentsScreen
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
import com.example.therapet.app.ui.screens.settings.accountmanagement.password.ResetPasswordScreen
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 05/01/2026
 *
 * These are helpers for screen UI tests e.g. launching screens
 */

object ScreenTestHelpers {

    //fake users for testing
    val testPatientUser: UserEntity = createTestUser(
        userid = "123456789123",
        firstname = "Bob",
        surname = "Bobbington",
        password = "Password_123",
        role = UserRole.PATIENT
    )

    val testTherapistUser: UserEntity = createTestUser(
        userid = "1231231231231234",
        firstname = "Jane",
        surname = "Doe",
        password = "Password_123",
        role = UserRole.THERAPIST
    )

    private fun fakeTherapists(): List<AccountUIModel> {
        return listOf(
            AccountUIModel(
                userid = "1234567891234567",
                fullName = "Bob Bobbington",
                role = UserRole.THERAPIST,
                lastLoginMillis = 1_800_000_000_000L
            ),
            AccountUIModel(
                userid = "1231231231231234",
                fullName = "Jane Doe",
                role = UserRole.THERAPIST,
                lastLoginMillis = 1_800_000_000_000L
            )
        )
    }

    // For appointments tests
    private fun fakeAppointments(): List<AppointmentEntity> {
        return listOf(
            AppointmentEntity(
                appointmentId = 1,
                therapistUserId = "therapist1",
                appointmentDateTime = 1_000_000L,
                appointmentType = AppointmentType.SESSION,
                patientUserId = null,
                isBooked = false
            )
        )
    }

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
                    hibernationEnabled = false,
                    onHibernationChanged = {},
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

    fun launchTherapistAppointmentsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ){
        composeRule.setContent{
            TheraPetTheme{
                AppointmentsScreen(
                    role = UserRole.THERAPIST,
                    appointments = fakeAppointments(),
                    onBack = {},
                    onAddAppointment = { _, _ -> },
                    onUpdateAppointment = {},
                    onDeleteAppointment = {},
                    getPatientName = { "Test Patient" }
                )
            }
        }
    }

    /** Launchers for patient appointment screen **/

    fun launchPatientAppointmentsScreen(
        composeRule: AndroidComposeTestRule<*, ComponentActivity>
    ) {
        composeRule.setContent {
            TheraPetTheme {
                PatientAppointmentsScreen(
                    step = BookingStep.PATIENT_APPOINTMENTS,
                    therapists = emptyList(),
                    selectedTherapistId = null,
                    selectedYearMonth = null,
                    appointments = emptyList(),
                    patientAppointments = fakeAppointments(),
                    onTherapistSelected = {},
                    onMonthSelected = {},
                    onAppointmentClick = {},
                    onBack = {},
                    onBook = {}
                )
            }
        }
    }

    fun launchChooseTherapistStep(composeRule: AndroidComposeTestRule<*, ComponentActivity>) {
        composeRule.setContent {
            TheraPetTheme {
                PatientAppointmentsScreen(
                    step = BookingStep.CHOOSE_THERAPIST,
                    therapists = fakeTherapists(),
                    selectedTherapistId = null,
                    selectedYearMonth = null,
                    appointments = emptyList(),
                    patientAppointments = emptyList(),
                    onTherapistSelected = {},
                    onMonthSelected = {},
                    onAppointmentClick = {},
                    onBack = {},
                    onBook = {}
                )
            }
        }
    }

    fun launchBookAppointmentStep(composeRule: AndroidComposeTestRule<*, ComponentActivity>) {
        composeRule.setContent {
            TheraPetTheme {
                PatientAppointmentsScreen(
                    step = BookingStep.BOOK_APPOINTMENT,
                    therapists = emptyList(),
                    selectedTherapistId = "123",
                    selectedYearMonth = null,
                    appointments = fakeAppointments(),
                    patientAppointments = emptyList(),
                    onTherapistSelected = {},
                    onMonthSelected = {},
                    onAppointmentClick = {},
                    onBack = {},
                    onBook = {}
                )
            }
        }
    }

    fun launchBookAppointmentStepEmpty(composeRule: AndroidComposeTestRule<*, ComponentActivity>) {
        composeRule.setContent {
            TheraPetTheme {
                PatientAppointmentsScreen(
                    step = BookingStep.BOOK_APPOINTMENT,
                    therapists = emptyList(),
                    selectedTherapistId = "123",
                    selectedYearMonth = null,
                    appointments = emptyList(),
                    patientAppointments = emptyList(),
                    onTherapistSelected = {},
                    onMonthSelected = {},
                    onAppointmentClick = {},
                    onBack = {},
                    onBook = {}
                )
            }
        }
    }

    /** -- **/

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
                    onBack = {},
                    errorMessage = null,
                    onResetPassword = { _, _ -> }
                )
            }
        }
    }
}