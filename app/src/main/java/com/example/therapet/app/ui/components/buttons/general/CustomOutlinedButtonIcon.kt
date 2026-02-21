package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.data.util.sounds.SoundManager
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 23/12/2025
 *
 * A custom outlined button with an icon that can sit either before, after of underneath the text using the OutlinedButton object
 */

// To determine where the icon in the below button sits
enum class IconPosition(){
    START,
    END,
    BOTTOM
}

//Outlined with icon (using class above)
@Composable
fun CustomOutlinedButtonIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    icon: @Composable (() -> Unit)? = null,
    iconPosition: IconPosition = IconPosition.BOTTOM
) {
    OutlinedButton(
        onClick = {onClick()
            SoundManager.playSound("click")},
        modifier = modifier
            .height(96.dp)
            .fillMaxWidth()
    ) {
        when (iconPosition) {

            IconPosition.BOTTOM -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium
                    )

                    if (icon != null) {
                        Spacer(modifier = Modifier.height(15.dp))
                        icon()
                    }
                }
            }

            IconPosition.START,
            IconPosition.END -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (icon != null && iconPosition == IconPosition.START) {
                        icon()
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium
                    )

                    if (icon != null && iconPosition == IconPosition.END) {
                        Spacer(modifier = Modifier.width(8.dp))
                        icon()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlinedButtonIconPreview() {
    TheraPetTheme {
        CustomOutlinedButtonIcon(onClick = {}, text = "Outlined with icon")
    }
}