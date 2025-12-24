package com.example.therapet.app.ui.components.fields.input

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A custom text field using TextField, colours have been overridden by TheraPet theme colours
 */

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(placeholder, style = MaterialTheme.typography.labelSmall) },
        label = { Text(label, style = MaterialTheme.typography.labelSmall) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    TheraPetTheme {
        CustomTextField(
            value = "Text Field",
            onValueChange = {},
            placeholder = "Text field",
            label = "Text field label")
    }
}