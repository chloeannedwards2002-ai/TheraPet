package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom filled button using the Button object
 */

@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true)
    {
    Button(onClick = { onClick() }, modifier = modifier, enabled = enabled) {
        Text(text,
            style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomFilledButtonPreview() {
    TheraPetTheme {
        CustomFilledButton(onClick = {}, text = "Filled")
    }
}