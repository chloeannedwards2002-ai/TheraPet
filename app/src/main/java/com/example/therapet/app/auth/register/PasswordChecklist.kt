package com.example.therapet.app.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height

@Composable
fun PasswordChecklist(
    requirements: List<PasswordRequirement>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        requirements.forEach { requirement ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (requirement.reqIsMet) {
                    Text(
                        text = "✓",
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                }

                Text(
                    text = requirement.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (requirement.reqIsMet)
                        Color(0xFF2E7D32)
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}