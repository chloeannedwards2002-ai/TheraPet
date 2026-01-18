package com.example.therapet.app.ui.components.fields.read_only

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A read only field
 */

@Composable
fun ReadOnlyField(
    value: String,
    modifier: Modifier = Modifier,
    label: String
){
    OutlinedTextField(
        label = { Text(label) },
        value = value,
        onValueChange = {},
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.labelSmall,
        readOnly = true,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun ReadOnlyFieldPreview() {
    TheraPetTheme {
        ReadOnlyField(
            value = "Text Field",
            label = ""
        )
    }
}