package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.theme.Blue2
import com.example.therapet.app.ui.theme.Grey
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom elevated button using the ElevatedButton object
 */

@Composable
fun CustomElevatedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit)
{
    ElevatedButton(onClick = { onClick() },
        colors = ButtonDefaults.elevatedButtonColors(
            // Overriding colours
            containerColor = Blue2,
            contentColor = Grey
        ), modifier = modifier
    ) {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomElevatedButtonPreview() {
    TheraPetTheme {
        CustomElevatedButton(onClick = {}, text = "Elevated")
    }
}