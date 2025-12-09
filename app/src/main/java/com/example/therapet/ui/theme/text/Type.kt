package com.example.therapet.ui.theme.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therapet.ui.theme.Grey
import com.example.therapet.ui.theme.TheraPetTheme

// Set of Material typography styles to start with
val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Grey
    ),

    labelMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = Grey
    ) ,

    labelLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        color = Grey
    ),

    bodySmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Light,
        fontStyle = Italic,
        fontSize = 14.sp,
        color = Grey
    ),

    bodyMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Light,
        fontStyle = Italic,
        fontSize = 25.sp,
        color = Grey
    ) ,

    bodyLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Light,
        fontStyle = Italic,
        fontSize = 40.sp,
        color = Grey
    ),

    titleSmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Grey
    ),

    titleMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Grey
    ),

    titleLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        color = Grey
    ),
)


// A composable to list all of the font styles and weights for future reference
@Composable
fun Test(modifier: Modifier = Modifier) {
val styles = listOf(
    MaterialTheme.typography.bodySmall,
    MaterialTheme.typography.bodyMedium,
    MaterialTheme.typography.bodyLarge,
    MaterialTheme.typography.labelSmall,
    MaterialTheme.typography.labelMedium,
    MaterialTheme.typography.labelLarge,
    MaterialTheme.typography.titleSmall,
    MaterialTheme.typography.titleMedium,
    MaterialTheme.typography.titleLarge
    )

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        styles.forEach { style ->
            Text(
                text = "Welcome to TheraPet",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = style
            )
        }
    }
}

 //Preview of all the styles
    @Preview(showBackground = true)
    @Composable
    fun FontPreview() {
     TheraPetTheme {
         Test()
     }
    }
