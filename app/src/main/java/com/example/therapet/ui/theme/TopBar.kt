package com.example.therapet.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TheraPetTheme {
        CompileAllTopBars()
    }
}

