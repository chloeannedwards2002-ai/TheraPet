package com.example.therapet.app.ui.components.buttons.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom circular button used on the home screen to care for the pet
 */

@Composable
fun PetNeedButton(onClick: () -> Unit) {
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
            imageVector = Icons.Default.Pets, // Will be parametized later on to apply either water, food or sleep icon
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PetNeedButtonPreview() {
    TheraPetTheme {
        PetNeedButton(onClick = {})
    }
}