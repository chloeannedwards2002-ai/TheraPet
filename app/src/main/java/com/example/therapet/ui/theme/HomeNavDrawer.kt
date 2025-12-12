package com.example.therapet.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp


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
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                        .testTag("nav_drawer")
                )

                NavigationDrawerItem(
                    label = { Text("") },
                    selected = false,
                    onClick = { onDestinationClicked("") }
                )
                NavigationDrawerItem(
                    label = { Text("") },
                    selected = false,
                    onClick = { onDestinationClicked("") }
                )
                NavigationDrawerItem(
                    label = { Text("") },
                    selected = false,
                    onClick = { onDestinationClicked("") }
                )
            }
        },
        content = content
    )
}