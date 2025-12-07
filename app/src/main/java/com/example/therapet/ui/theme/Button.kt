package com.example.therapet.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Filled
@Composable
fun MyFilledButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }, modifier = modifier) {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

// Filled tonal
@Composable
fun MyFilledTonalButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    FilledTonalButton(onClick = { onClick() }, modifier = modifier) {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

// Elevated
@Composable
fun MyElevatedButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Blue2,
            contentColor = Grey
        ), modifier = modifier
    ) {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

// Outlined
@Composable
fun MyOutlinedButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }, modifier = modifier)  {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

// Text
@Composable
fun MyTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() }, modifier = modifier) {
        Text(text,
            style = MaterialTheme.typography.labelSmall)
    }
}

/* A grid to compose all buttons for preview */
@Composable
fun AllButtonsGrid() {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        item { MyFilledButton(text = "Filled") { } }
        item { MyFilledTonalButton(text = "Tonal") { } }
        item { MyElevatedButton(text = "Elevate") { } }
        item { MyOutlinedButton(text = "Outline") { } }
        item { MyTextButton(text = "Text") { } }
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    TheraPetTheme {
        AllButtonsGrid()
    }
}

