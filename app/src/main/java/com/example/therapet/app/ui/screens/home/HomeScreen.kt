package com.example.therapet.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.therapet.app.ui.components.bars.MainTopBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.therapet.R
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.HomeNavigationDrawer
import com.example.therapet.app.ui.components.bars.PetCareBar
import com.example.therapet.app.ui.components.buttons.home.CircularButton
import com.example.therapet.app.ui.components.fields.account.AccountDetailsDialog
import com.example.therapet.app.ui.components.fields.account.MinimizedAccountCell
import com.example.therapet.app.ui.components.pet.PetPenguin
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.coroutines.launch
import com.example.therapet.app.ui.theme.PetColours
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Home screen UI
 *
 * Repeated if statements about PATIENT role are no optimal but will work for now
 *
 * Displays different content depending on users role
 * PATIENT: Shows petpenguin with interactive petcarebar
 * THERAPIST: Shows watchlist of patients
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    role: UserRole,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onSettings: () -> Unit,
    onNotifs: () -> Unit,
    onAppts: () -> Unit,
    onBookAppt: () -> Unit,
    onProfile: () -> Unit,
    user: UserEntity?,
    watchlist: List<AccountUIModel> = emptyList(),
    selectedAccount: AccountUIModel? = null,
    onAccountRemove: (AccountUIModel) -> Unit = {},
    onAccountSelected: (AccountUIModel) -> Unit = {},
    nextAppointment: AppointmentEntity? = null,
    remindersEnabled: Boolean,
    onColourSelected: (Int) -> Unit,
    petColourIndex: Int,
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    var localSelectedAccount by remember { mutableStateOf<AccountUIModel?>(null) }

    HomeNavigationDrawer(
        drawerState = drawerState,
        user = user,
        onDestinationClicked = { destination ->
            when (destination) {
                "settings" -> onSettings()
                "logout" -> onLogout()
                "notifications" -> onNotifs()
                "appointments" -> onAppts()
                "profile" -> onProfile()
            }
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    text = stringResource(R.string.app_name),
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onApptsClick = onAppts
                )
            },
            floatingActionButton = {
                if (role == UserRole.PATIENT) {
                    CircularButton(
                        onClick = onBookAppt,
                        modifier = Modifier.padding(16.dp),
                        enabled = true
                    )
                }
            }
        ) { innerPadding ->

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                if (role == UserRole.PATIENT) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .align(Alignment.End)
                        ) {

                            Text(
                                text = "Change Colour",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .clickable { expanded = true }
                                    .testTag("colour_dropdown_button")
                            )

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                PetColours.forEachIndexed { index, colour ->
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(36.dp)
                                            .background(colour, RoundedCornerShape(8.dp))
                                            .clickable {
                                                onColourSelected(index)
                                                expanded = false
                                            }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(60.dp))

                        // 🔹 Pet Section
                        Box {

                            PetPenguin(
                                bodyColour = PetColours.getOrNull(petColourIndex),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .aspectRatio(1f)
                            )

                            if (nextAppointment != null && remindersEnabled) {
                                PetReminderBubble(
                                    appointment = nextAppointment,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .offset(x = 40.dp, y = (-20).dp)
                                )
                            }
                        }
                    }
                }

                if (role == UserRole.THERAPIST) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Watchlist",
                            style = MaterialTheme.typography.labelMedium
                        )

                        watchlist.forEach { account ->
                            MinimizedAccountCell(
                                account = account,
                                onClick = { localSelectedAccount = account }
                            )
                        }
                    }

                    AccountDetailsDialog(
                        account = localSelectedAccount,
                        onDismiss = { localSelectedAccount = null },
                        onRemoveClick = { localSelectedAccount?.let(onAccountRemove) }
                    )
                }
            }
        }
    }
}

/**
 * Pet reminder bubble composable
 */

@Composable
fun PetReminderBubble(
    appointment: AppointmentEntity,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(true) }

    // Auto-dismiss after 4 seconds
    LaunchedEffect(appointment) {
        visible = true
        delay(4000)
        visible = false
    }

    if (!visible) return

    val formattedDate = remember(appointment) {
        SimpleDateFormat(
            "dd.MM.yyyy HH:mm",
            Locale.getDefault()
        ).format(java.util.Date(appointment.appointmentDateTime))
    }

    Box(
        modifier = modifier
            .padding(16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
            .zIndex(1f)
    ) {
        Text(
            text = "Your next appointment is at\n$formattedDate",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPatientPreview() {
    TheraPetTheme {
        HomeScreen(
            role = UserRole.PATIENT,
            onLogout = {},
            onSettings = {},
            onNotifs = {},
            onAppts = {},
            onBookAppt = {},
            onProfile = {},
            petColourIndex = 5,
            user = null,
            remindersEnabled = true,
            onColourSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenTherapistPreview() {
    TheraPetTheme {
        HomeScreen(
            role = UserRole.THERAPIST,
            onLogout = {},
            onSettings = {},
            onNotifs = {},
            onAppts = {},
            onBookAppt = {},
            onProfile = {},
            petColourIndex = 0,
            user = null,
            remindersEnabled = false,
            onColourSelected = {}
        )
    }
}