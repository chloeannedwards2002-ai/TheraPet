package com.example.therapet.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.therapet.app.ui.components.bars.MainTopBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.therapet.R
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.components.HomeNavigationDrawer
import com.example.therapet.app.ui.components.bars.PetCareBar
import com.example.therapet.app.ui.components.buttons.home.CircularButton
import com.example.therapet.app.ui.components.pet.PetPenguin
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.coroutines.launch
import com.example.therapet.app.ui.theme.PetColours

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Home screen UI
 *
 * Repeated if statements about PATIENT role are no optimal but will work for now
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    role: UserRole,
    petColourIndex: Int,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onSettings: () -> Unit,
    onNotifs:() -> Unit,
    onAppts:() -> Unit,
    onBookAppt: () -> Unit,
    onProfile: () -> Unit,
    user: UserEntity?,
    onFoodIncrease: () -> Unit = {},
    onWaterIncrease: () -> Unit = {},
    foodLevel: Float = 0.2f,
    waterLevel: Float = 0.2f,
    sleepLevel: Float = 1f,
    isSleeping: Boolean = false,
    onSleepClick: () -> Unit = {},
    isHibernating: Boolean = false
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                if(role == UserRole.PATIENT){ 
                    CircularButton(
                        onClick = onBookAppt,
                        modifier = Modifier
                            .padding(16.dp)
                            .testTag("book_appointment_button"),
                        enabled = true
                    )
                }
            },

            topBar = {
                MainTopBar(
                    text = stringResource(R.string.app_name),
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    },
                    onApptsClick = onAppts
                )
            },
            bottomBar = {
                if(role == UserRole.PATIENT){
                    PetCareBar(
                        modifier = Modifier.zIndex(0f),
                        foodLevel = foodLevel,
                        waterLevel = waterLevel,
                        sleepLevel = sleepLevel,
                        isSleeping = isSleeping,
                        onFoodIncrease = onFoodIncrease,
                        onWaterIncrease = onWaterIncrease,
                        onSleepClick = onSleepClick,
                        isHibernating = isHibernating
                    )
                }
            }
        ) { innerPadding ->

            if(role == UserRole.PATIENT) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .testTag("home_screen"),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(150.dp))

                    PetPenguin(
                        bodyColour = PetColours.getOrNull(petColourIndex),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                           .offset(y = -50.dp)
                            .weight(0.25f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
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
            user = null
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
            user = null
        )
    }
}