package com.example.therapet.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.therapet.R
import androidx.compose.ui.res.stringResource
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton


/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Home nav drawer UI
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    user: UserEntity?,
    onDestinationClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.testTag("nav_drawer")
            ) {
                DrawerHeader(user = user)

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_profile_button"),
                    text = stringResource(R.string.profile),
                    onClick = { onDestinationClicked("profile") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_appointments_button"),
                    text = stringResource(R.string.appointments),
                    onClick = { onDestinationClicked("appointments") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_notifications_button"),
                    text = stringResource(R.string.notifications),
                    onClick = { onDestinationClicked("notifications") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("drawer_settings_button"),
                    text = stringResource(R.string.settings),
                    onClick = { onDestinationClicked("settings") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedButton(
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

// drawer header
@Composable
fun DrawerHeader(
    user: UserEntity?,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ProfileAvatar(
            modifier = Modifier.size(56.dp)
                .testTag("drawer_profile_avatar")
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column{
            Text(
                text = user?.let { "${it.firstname} ${it.surname}" } ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.testTag("full_name")
            )
        }
    }
}