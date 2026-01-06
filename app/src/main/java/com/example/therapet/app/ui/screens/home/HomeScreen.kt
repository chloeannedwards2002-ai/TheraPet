package com.example.therapet.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.therapet.R
import com.example.therapet.app.ui.components.HomeNavigationDrawer
import com.example.therapet.app.ui.components.bars.PetCareBar
import com.example.therapet.app.ui.components.buttons.home.CircularButton
import com.example.therapet.app.ui.screens.pet.PetPlaceholder
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.coroutines.launch

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Home screen UI
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onSettings: () -> Unit,
    onNotifs:() -> Unit,
    onAppts:() -> Unit,
    onBookAppt: () -> Unit,
    onProfile: () -> Unit
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    HomeNavigationDrawer(
        drawerState = drawerState,
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
            floatingActionButton = {
                CircularButton(
                    onClick = onBookAppt,
                    modifier = Modifier
                        .padding(16.dp),
                    testTag = "choose_therapist_button"
                )
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
                PetCareBar()
            }
        ) { innerPadding ->

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("home_screen"),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(150.dp))

                PetPlaceholder()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TheraPetTheme {
        HomeScreen(
            onLogout = {},
            onSettings = {},
            onNotifs = {},
            onAppts = {},
            onBookAppt = {},
            onProfile = {}
        )
    }
}