package com.example.therapet.ui.theme

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
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
fun MyFilledTonalButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelMedium
        )
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
fun MyOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(onClick = onClick, modifier = modifier,
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

// To determine where the icon in the below button sits
enum class IconPosition(){
    START,
    END,
    BOTTOM
}

//Outlined with icon (using class above)
@Composable
fun MyOutlinedButtonIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    icon: (@Composable () -> Unit)? = null,
    iconPosition: IconPosition = IconPosition.BOTTOM
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(96.dp)
            .fillMaxWidth()
    ) {
        when (iconPosition) {

            IconPosition.BOTTOM -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium
                    )

                    if (icon != null) {
                        Spacer(modifier = Modifier.height(15.dp))
                        icon()
                    }
                }
            }

            IconPosition.START,
            IconPosition.END -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (icon != null && iconPosition == IconPosition.START) {
                        icon()
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium
                    )

                    if (icon != null && iconPosition == IconPosition.END) {
                        Spacer(modifier = Modifier.width(8.dp))
                        icon()
                    }
                }
            }
        }
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

// Custom buttons for the (placeholder for now not an actual button yet)
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

// Book appointment button
@Composable
fun CircularAppointmentButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(size = 60.dp)
            .background (
                MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .clickable { onClick() }
            .testTag("choose_therapist_button"),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }
}

/* Toggle field */

@Composable
fun ToggleField(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 1.dp,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
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
        item { MyFilledButton(text = "Filled", onClick = {}) }
        item { MyFilledTonalButton(text = "Tonal", onClick = {}) }
        item { MyElevatedButton(text = "Elevate", onClick = {}) }
        item { MyOutlinedButton(text = "Outline", onClick = {}) }
        item { MyTextButton(text = "Text", onClick = {}) }
        item { CircularBarButton(onClick = {}) }
        item { CircularAppointmentButton(onClick = {}) }
        item { ToggleField(label = "Toggle", checked = false, onCheckedChange = { }) }
        item { MyOutlinedButtonIcon(onClick = {}, text = "Outline Icon")}
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    TheraPetTheme {
        AllButtonsGrid()
    }
}

