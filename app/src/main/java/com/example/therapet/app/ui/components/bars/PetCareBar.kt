package com.example.therapet.app.ui.components.bars

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.buttons.home.CircularButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.testTag

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A custom bottom bar used only on the home screen. includes 3 buttons for pet care
 * TODO: Implement pet care for each button & add pet need bars on the sides
 */

@Composable
fun PetCareBar(
    foodLevel: Float,
    waterLevel: Float,
    onFoodIncrease: () -> Unit,
    onWaterIncrease:() -> Unit
) {
    val animatedFood by animateFloatAsState(foodLevel, animationSpec = tween(400))
    val animatedWater by animateFloatAsState(waterLevel, animationSpec = tween(400))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .testTag("pet_care_bar")
    ) {
        val sideWidth = 0.18f

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 55.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FoodCircularButton(
                        onClick = onFoodIncrease,
                        modifier = Modifier.testTag("food_button")
                    )

                    WaterCircularButton(onClick = onWaterIncrease,
                        modifier = Modifier.testTag("water_button")
                    )

                    SleepCircularButton(onClick = { /* TODO sleep */ },
                        modifier = Modifier.testTag("sleep_button"))
                }
            }
        }

        //left bar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(sideWidth)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.BottomStart),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                
                //Food need bar
                PetNeedBar(
                    modifier = Modifier
                        .fillMaxHeight(0.8f),
                    width = 18.dp,
                    fillFraction = animatedFood,
                    testTag = "food_need_bar"
                )

                //Water need bar
                PetNeedBar(
                    modifier = Modifier
                        .fillMaxHeight(0.8f),
                    width = 18.dp,
                    fillFraction = animatedWater,
                    testTag = "water_need_bar"
                )
            }
        }

        // Right vertical bar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(sideWidth)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.BottomEnd),
            contentAlignment = Alignment.TopCenter
        ) {

            // Sleep need bar
            PetNeedBar(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .padding(top = 12.dp),
                width = 18.dp,
                fillFraction = 0.85f,
                testTag = "sleep_need_bar"
            )
        }
    }
}

/**
 * Pet care customized buttons
 */

@Composable
fun FoodCircularButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    CircularButton(
        onClick = onClick,
        modifier = modifier,
        iconVector = Icons.Filled.Fastfood,
        contentDescription = "Food"
    )
}

@Composable
fun WaterCircularButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    CircularButton(
        onClick = onClick,
        modifier = modifier,
        iconVector = Icons.Filled.LocalDrink,
        contentDescription = "Water"
    )
}

@Composable
fun SleepCircularButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    CircularButton(
        onClick = onClick,
        modifier = modifier,
        iconVector = Icons.Filled.Bedtime,
        contentDescription = "Sleep"
    )
}

/*@Preview(showBackground = true)
@Composable
fun PetCareBarPreview() {
    TheraPetTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PetCareBar()
        }
    }
}*/