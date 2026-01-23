package com.example.therapet.app.ui.components.buttons.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun ResetPasswordButton(
    label: String,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .testTag(testTag)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}