package com.example.therapet.app.ui.screens.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Choose therapist screen UI
 */

@Composable
fun ChooseTherapistScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {
    Scaffold(
        topBar = {
            BasicTopBar(
                text = stringResource(R.string.choose_therapist),
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            ContinueButton(
                onClick = onContinue,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .testTag("choose_therapist_screen")
        ){

        }
    }
}

// Continue button
@Composable
fun ContinueButton(onClick: () -> Unit){
    CustomFilledButton(
        onClick = onClick,
        text = stringResource(R.string.continue_button),
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .testTag("continue_button")
    )
}

@Preview(showBackground = true)
@Composable
fun ChooseTherapistPreview() {
    TheraPetTheme {
        ChooseTherapistScreen(
            onBack = {},
            onContinue = {}
        )
    }
}