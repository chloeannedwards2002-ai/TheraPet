package com.example.therapet.app.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.MyOutlinedButton
import com.example.therapet.R
import androidx.compose.ui.res.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    onDestinationClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                Text(
                    text = stringResource(R.string.nav_drawer_menu),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                        .testTag("nav_drawer"),
                )

                Spacer(modifier = Modifier.height(20.dp))

                MyOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_profile_button"),
                    text = stringResource(R.string.profile),
                    onClick = { onDestinationClicked("profile") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                MyOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_appointments_button"),
                    text = stringResource(R.string.appointments),
                    onClick = { onDestinationClicked("appointments") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                MyOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_notifications_button"),
                    text = stringResource(R.string.notifications),
                    onClick = { onDestinationClicked("notifications") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                MyOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_settings_button"),
                    text = stringResource(R.string.settings),
                    onClick = { onDestinationClicked("settings") }
                )

                Spacer(modifier = Modifier.weight(1f))

                MyOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_logout_button"),
                    text = stringResource(R.string.logout),
                    onClick = { onDestinationClicked("logout") }
                )
            }
        },
        content = content
    )
}