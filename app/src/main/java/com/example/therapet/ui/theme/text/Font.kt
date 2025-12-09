package com.example.therapet.ui.theme.text

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.therapet.R

// This file defines the default font style of TheraPet as "Nunito"

val NunitoFamily = FontFamily(
    // Black
    Font(R.font.nunito_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.nunito_blackitalic, FontWeight.Black, FontStyle.Italic),

    // Bold
    Font(R.font.nunito_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.nunito_bolditalic, FontWeight.Bold, FontStyle.Italic),

    // Extra Bold
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.nunito_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),

    // Extra light
    Font(R.font.nunito_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.nunito_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),

    // Italic
    Font(R.font.nunito_italic, FontWeight.Normal, FontStyle.Italic),

    // Variable Italic
    Font(R.font.nunito_italic_variablefont_wght, FontWeight.Normal, FontStyle.Italic),

    // Light
    Font(R.font.nunito_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.nunito_lightitalic, FontWeight.Light, FontStyle.Italic),

    // Medium
    Font(R.font.nunito_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.nunito_mediumitalic, FontWeight.Medium, FontStyle.Italic),

    // Regular
    Font(R.font.nunito_regular, FontWeight.Normal, FontStyle.Normal),

    // Semi-bold
    Font(R.font.nunito_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.nunito_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),

    // Variable regular
    Font(R.font.nunito_variablefont_wght, FontWeight.Normal, FontStyle.Normal),
)
