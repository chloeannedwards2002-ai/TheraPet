package com.example.therapet.app.ui.components.fields.read_only

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 24/12/2025
 *
 * A read only field that has an edit button (mostly used on profile screen)
 */

@Composable
fun ReadOnlyEditableField(
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

@Preview(showBackground = true)
@Composable
fun ReadOnlyEditableFieldPreview() {
    TheraPetTheme {
        ReadOnlyEditableField(
            value = "Text Field",
            onEditClick = {},
            editTestTag = "Test"
        )
    }
}