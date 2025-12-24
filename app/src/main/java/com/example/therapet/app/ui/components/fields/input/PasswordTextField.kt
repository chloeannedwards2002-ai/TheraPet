package com.example.therapet.app.ui.components.fields.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A custom password text field using OutlinedTextField with a toggle button that shows and hides the text in the field
 */

@Composable
fun CustomPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    testTag: String? = null
){
    var showPassword by remember { mutableStateOf(false)}

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        label = { Text(label, style = textStyle) },
        placeholder = { Text(placeholder, style = textStyle) },
        textStyle = textStyle,
        singleLine = true,

        // Obscuring the password logic
        visualTransformation = if(showPassword)
            VisualTransformation.None
        else PasswordVisualTransformation(),

        trailingIcon = {
            Icon(
                imageVector = if(showPassword)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff,
                contentDescription = "Toggle password visibility",
                modifier = Modifier
                    .clickable{ showPassword = !showPassword }
                    .then(if (testTag != null) Modifier.testTag(testTag) else Modifier)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomPasswordTextFieldPreview() {
    TheraPetTheme {
        CustomPasswordTextField(
            value = "Password Text Field",
            onValueChange = {},
            placeholder = "Password Text field",
            label = "Password text field label")
    }
}