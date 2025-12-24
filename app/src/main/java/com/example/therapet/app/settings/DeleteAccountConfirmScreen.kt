package com.example.therapet.app.settings

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
import com.example.therapet.R
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton
import com.example.therapet.app.ui.components.buttons.general.CustomTonalFilledButton
import com.example.therapet.app.ui.theme.TheraPetTheme


/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Delete account confirmation screen UI
 */

@Composable
fun DeleteAccountConfirmScreen(
    onBack: () -> Unit,
    onDeleteAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold() { innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.confirm_acc_deletion_title),
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
    CustomOutlinedButton(
        modifier = modifier,
        text = stringResource(R.string.cancel),
        onClick = onClick
    )
}

// Delete Account button
@Composable
fun DeleteAccountButton(modifier: Modifier = Modifier, onClick: () -> Unit){
    CustomTonalFilledButton(
        text = stringResource(R.string.delete_account),
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