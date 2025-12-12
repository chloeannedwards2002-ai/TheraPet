package com.example.therapet.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)

// Basic top bar with nullable back button
@Composable
fun BasicTopBar(
    text: String,
    onBackClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            if(onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = { /* No actions */ }
    )
}

// Main top bar for the home screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(text: String, onMenuClick: () -> Unit) {
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
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.CalendarMonth, contentDescription = "Calendar")
            }
        }
    )
}

// Home screen bottom bar shape
// A U shaped UI component that will house the interaction buttons and need bars
@Composable
fun CustomBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularBarButton(onClick = { /* TODO food */ })
            CircularBarButton(onClick = { /* TODO water */ })
            CircularBarButton(onClick = { /* TODO sleep */ })
        }
    }
}

@Composable
fun CompileAllTopBars(){
    Column {
        // Top bar with a back button
        BasicTopBar(
            text = "With Back Button",
            onBackClick = {},
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Top bar without a back button
        BasicTopBar(
            text = "Without Back Button",
            onBackClick = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        MainTopBar(text = "Main Top Bar", onMenuClick = {})

        Spacer(modifier = Modifier.height(16.dp))

        CustomBottomBar()
    }
}



@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TheraPetTheme {
        CompileAllTopBars()
    }
}

