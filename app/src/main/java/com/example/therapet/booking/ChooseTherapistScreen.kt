package com.example.therapet.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyFilledButton
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun ChooseTherapistScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {
    Scaffold(
        topBar = {
            BasicTopBar(
                text = "Choose Therapist",
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
                .testTag("choose_therapist_screen")
        ){

        }
    }
}

// Continue button
@Composable
fun ContinueButton(onClick: () -> Unit){
    MyFilledButton(
        onClick = onClick,
        text = "Continue",
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