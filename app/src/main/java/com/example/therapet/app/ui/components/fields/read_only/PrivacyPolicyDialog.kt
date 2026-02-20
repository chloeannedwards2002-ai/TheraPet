package com.example.therapet.app.ui.components.fields.read_only

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButtonIcon
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 20/02/2026
 *
 * Privacy policy dialog
 */

@Composable
fun PrivacyPolicyDialog(
    onDismiss: () -> Unit,
    onAccepted: () -> Unit
) {
    var acceptedPolicy by remember { mutableStateOf(false) }
    var acceptedData by remember { mutableStateOf(false) }

    val canContinue = acceptedPolicy && acceptedData

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {},
        modifier = Modifier.testTag("privacy_policy_dialog"),
        containerColor = Color.White,
        title = {
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {

                // Scrollable policy text
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = """
                            Welcome to TheraPet.
                            
                            We collect your user ID, name, and account details 
                            to provide personalised therapy tracking services.
                            
                            Your information is stored securely and will never 
                            be shared with third parties without consent.
                            
                            You may request deletion of your account at any time.
                            
                            By continuing, you agree to our data handling practices.
                        """.trimIndent(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = acceptedPolicy,
                        onCheckedChange = { acceptedPolicy = it },
                        modifier = Modifier.testTag("accept_policy_checkbox")
                    )
                    Text("I have read and accept the Privacy Policy",
                        style = MaterialTheme.typography.labelSmall)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = acceptedData,
                        onCheckedChange = { acceptedData = it },
                        modifier = Modifier.testTag("accept_data_checkbox")
                    )
                    Text("I consent to my data being processed",
                        style = MaterialTheme.typography.labelSmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel",
                            style = MaterialTheme.typography.labelMedium)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onAccepted,
                        enabled = canContinue,
                        modifier = Modifier.testTag("accept_privacy_button")
                    ) {
                        Text("Accept",
                            style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyPreview() {
    TheraPetTheme {
        PrivacyPolicyDialog(
            onDismiss = {},
            {}
        )
    }
}