package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.data.util.sounds.SoundManager
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom text button using the TextButton object
 */

@Composable
fun CustomTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick()
            SoundManager.playSound("click")}, modifier = modifier) {
        Text(text,
            style = MaterialTheme.typography.labelSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextButtonPreview() {
    TheraPetTheme {
        CustomTextButton(onClick = {}, text = "Text")
    }
}