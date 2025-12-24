package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom outlined button using the OutlinedButton object
 */

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(onClick = onClick, modifier = modifier,
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlinedButtonPreview() {
    TheraPetTheme {
        CustomOutlinedButton(onClick = {}, text = "Outlined")
    }
}