package com.example.therapet.ui.theme

import androidx.compose.material3.Checkbox
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

// Outlined text field
@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(50.dp),
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

// Outlined text field
@Composable
fun MyOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        placeholder = { Text(placeholder, style = textStyle) },
        label = { Text(label, style = textStyle) },
        textStyle = textStyle,
        singleLine = true
    )
}

// Password text field
@Composable
fun MyPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    toggleTestTag: String? = null
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
        textStyle = MaterialTheme.typography.labelSmall,
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
                    .then(if (toggleTestTag != null) Modifier.testTag(toggleTestTag) else Modifier)
            )
        }
    )
}

@Composable
fun MyCheckBox(
    modifier: Modifier = Modifier,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    testTag: String = "remember_password_tick_box"
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
                .testTag(testTag)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            // Manually overriding the checkbox padding to bring the text closer to it slightly
            modifier = Modifier.offset(x = (-9).dp)
        )
    }
}

// Read only editable field

@Composable
fun MyReadOnlyEditableField(
    value: String,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    editTestTag: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.labelSmall,
        readOnly = true,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = onEditClick,
                modifier = Modifier.testTag(editTestTag)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    )
}

// Read only field
@Composable
fun MyReadOnlyField(
    value: String,
    modifier: Modifier = Modifier,
){
    OutlinedTextField(
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


@Composable
fun ShowInputFields() {

    // Example states for preview
    var exampleText1 by remember { mutableStateOf("") }
    var exampleText2 by remember { mutableStateOf("") }
    var exampleText3 by remember { mutableStateOf("") }
    var exampleCheck by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        MyTextField(
            value = exampleText1,
            onValueChange = { exampleText1 = it },
            placeholder = "Enter email",
            label = "Email"
        )

        MyOutlinedTextField(
            value = exampleText2,
            onValueChange = { exampleText2 = it },
            placeholder = "Enter name",
            label = "Name"
        )

        MyPasswordTextField(
            value = exampleText3,
            onValueChange = {exampleText3 = it },
            label = "Password",
            placeholder = "Enter password",
        )

        MyCheckBox(
            label = "Test",
            checked = exampleCheck,
            onCheckedChange = { exampleCheck = it }
        )

        MyReadOnlyEditableField(
            value = "Read-only text",
            onEditClick = { },
            editTestTag = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldsPreview() {
    TheraPetTheme {
        ShowInputFields()
    }
}