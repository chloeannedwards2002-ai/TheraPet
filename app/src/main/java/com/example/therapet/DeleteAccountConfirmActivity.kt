package com.example.therapet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.MyFilledTonalButton
import com.example.therapet.ui.theme.MyOutlinedButton
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun DeleteAccountConfirmScreen(
    onBack: () -> Unit,
    onDeleteAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold() { innerPadding ->

        BasicTopBar(
            text = "Confirm Account Deletion",
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("delete_account_confirm_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = stringResource(R.string.confirm_deletion),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            DeleteAccountButton(onClick = onDeleteAccount)

            Spacer(modifier = Modifier.height(20.dp))

            CancelButton(onClick = onBack, modifier = Modifier.fillMaxWidth())
        }   
    }
}

// Cancel button
@Composable
fun CancelButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    MyOutlinedButton(
        modifier = modifier,
        text = "Cancel",
        onClick = onClick
    )
}

// Delete Account button
@Composable
fun DeleteAccountButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    MyFilledTonalButton(
        text = "Delete Account",
        onClick = onClick,
        modifier = modifier
            .testTag("delete_account_confirm_button")
            .fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountConfirmPreview() {
    TheraPetTheme {
        DeleteAccountConfirmScreen(
            onBack = {},
            onDeleteAccount = {}
        )
    }
}