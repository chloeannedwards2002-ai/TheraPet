package com.example.therapet.ui.theme.customs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.TheraPetTheme

@OptIn(ExperimentalMaterial3Api::class)

/*
    Top Bars
 */

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
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = { /* No actions */ }
    )
}

// Home screen top bar with burger menu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(text: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = {}) {
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

/*
    Bottom bars
 */

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
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularBarButton(onClick = { /* TODO */ })
            CircularBarButton(onClick = { /* TODO */ })
            CircularBarButton(onClick = { /* TODO */ })
        }
    }
}

@Composable
fun CircularBarButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Pets,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Composable
fun compileAllBars() {
    Scaffold(
        bottomBar = {
            CustomBottomBar()
        }
    ) { innerPadding ->   // <-- This was missing
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            BasicTopBar(
                text = "With Back Button",
                onBackClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            BasicTopBar(
                text = "Without Back Button"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainTopBar(
                text = "Main Top Bar"
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TheraPetTheme {
        compileAllBars()
    }
}

