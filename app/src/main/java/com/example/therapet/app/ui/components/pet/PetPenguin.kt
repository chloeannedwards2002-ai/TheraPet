package com.example.therapet.app.ui.components.pet

import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.R
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 11/01/2026
 *
 * Penguin pet component - made up of multiple components stacked
 */

@Composable
fun PetPenguin
    (modifier: Modifier = Modifier,
     bodyColour: Color? = null){
    Box(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(R.drawable.penguin_feet),
            "penguin feet"
        )
        Image(
            painter = painterResource(R.drawable.penguin_belly),
            "penguin belly"
            //tinted by user selected colour
        )
        Image(
            painter = painterResource(R.drawable.penguin_body),
            "penguin body",
            colorFilter = bodyColour?.let { ColorFilter.tint(it) }
        )
        Image(
            painter = painterResource(R.drawable.penguin_eyes),
            "penguin eyes"
        )
        Image(
            painter = painterResource(R.drawable.penguin_beak),
            "penguin beak"
        )
        Image(
            painter = painterResource(R.drawable.penguin_blush),
            "penguin blush"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PetPenguinPreview() {
    TheraPetTheme {
        PetPenguin()
    }
}
