package com.example.therapet.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.PetColours

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * This is a custom carousel used on the create/edit pet screen to select the pets colour.
 * This functionality has yet to be implemented bu the carousel itself works
 */

val isSelectedCircleKey = SemanticsPropertyKey<Boolean>("IsSelectedCircle")
var SemanticsPropertyReceiver.isSelectedCircle by isSelectedCircleKey

@Composable
fun CircularCarousel(
    selectedIndex: Int,
    onIndexChanged: (Int) -> Unit,
    circleCount: Int = 7,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("colour_carousel")
    ){
        // Left Arrow
        IconButton(
            onClick = {
                if(selectedIndex > 0) {
                    onIndexChanged(selectedIndex - 1)
                }
            },
            modifier = Modifier.testTag("carousel_prev")
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous colour"
            )
        }

        // Circles
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            repeat(circleCount) {
                index ->
                val isSelected = index == selectedIndex
                val size = if (isSelected) 32.dp else 20.dp // Circle increases slightly in size when being "chosen"

                Box(
                    modifier = Modifier
                        .testTag("circle_$index")
                        .size(size)
                        .clip(CircleShape)
                        .background(PetColours[index])
                        .semantics{ isSelectedCircle = isSelected }
                )
            }
        }

        // Right arrow
        IconButton(
            onClick = {
                if (selectedIndex < circleCount - 1) {
                    onIndexChanged(selectedIndex + 1)
                }
            },
            modifier = Modifier.testTag("carousel_next")
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next colour"
            )
        }
    }
}