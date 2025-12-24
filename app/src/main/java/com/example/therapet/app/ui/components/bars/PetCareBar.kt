package com.example.therapet.app.ui.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.buttons.home.CircularButton

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A custom bottom bar used only on the home screen. includes 3 buttons for pet care
 * TODO: Implement pet care for each button & add pet need bars on the sides
 */

@Composable
fun PetCareBar() {
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
            CircularButton(onClick = { /* TODO food */ }, testTag = "food_button")
            CircularButton(onClick = { /* TODO water */ }, testTag = "water_button")
            CircularButton(onClick = { /* TODO sleep */ }, testTag = "sleep_button")
        }
    }
}