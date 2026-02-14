package com.example.therapet.app.ui.components.buttons.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom circular button used on the home screen to navigate to the Choose Therapist screen
 */

@Composable
fun CircularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconVector: ImageVector = Icons.Default.Add,
    contentDescription: String? = null
) {
    Surface(
        modifier = modifier.size(70.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        tonalElevation = 6.dp,
        shadowElevation = 6.dp,
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = iconVector,
                contentDescription = contentDescription,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircularButtonPreview() {
    TheraPetTheme {
        CircularButton(onClick = {})
    }
}