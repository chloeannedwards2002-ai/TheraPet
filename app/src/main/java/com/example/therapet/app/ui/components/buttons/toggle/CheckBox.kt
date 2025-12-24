package com.example.therapet.app.ui.components.buttons.toggle

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A custom tick box that can be reused in any screen
 */

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
){
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier
                .scale(0.75f)
                .testTag("checkBox")
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            // Manually overriding the checkbox padding to bring the text closer to it slightly
            modifier = Modifier.offset(x = (-9).dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    TheraPetTheme {
        CheckBox(
            checked = true,
            onCheckedChange = {},
            label = "Check box",
        )
    }
}