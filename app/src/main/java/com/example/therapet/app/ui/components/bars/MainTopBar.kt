package com.example.therapet.app.ui.components.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A top bar with burger menu icon, notifications and appointments button mainly used for the home screen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(text: String, onMenuClick: () -> Unit, onApptsClick: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = onMenuClick,
                modifier = Modifier.testTag("menu_button")) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = {
            IconButton(onClick = { } , modifier = Modifier.testTag("notifications_button")) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = onApptsClick, modifier = Modifier.testTag("appointments_button")) {
                Icon(Icons.Filled.CalendarMonth, contentDescription = "Calendar")
            }
        }
    )
}