package com.example.therapet.app.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButtonIcon
import com.example.therapet.app.ui.components.buttons.general.IconPosition

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Help and support screen UI
 */

@Composable
fun HelpSupportScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
){
    Scaffold(
    ) {
        innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.help_and_support),
            onBackClick = onBack,
        )
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("help_support_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(100.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CallUsButton(
                    modifier = Modifier.weight(1f)
                )
                EmailUsButton(
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.get_in_touch),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            InstagramButton()

            Spacer(modifier = Modifier.height(20.dp))

            FacebookButton()

            Spacer(modifier = Modifier.height(20.dp))

            WhatsappButton()

            Spacer(modifier = Modifier.height(20.dp))

            TwitterButton()
        }
    }
}

// Buttons for this screen
@Composable
fun CallUsButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.call_us),
        icon = {
            Icon(Icons.Default.Phone, contentDescription = null)
        },
        onClick = { }
    )
}

@Composable
fun EmailUsButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.email_us),
        icon = {
            Icon(Icons.Default.Email, contentDescription = null)
        },
        onClick = { }
    )
}

@Composable
fun InstagramButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.instagram),
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun FacebookButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.facebook),
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun WhatsappButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.whatsapp),
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun TwitterButton(modifier: Modifier = Modifier){
    CustomOutlinedButtonIcon(
        modifier = modifier,
        text = stringResource(R.string.twitter),
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}



@Preview(showBackground = true)
@Composable
fun HelpSupportPreview() {
    TheraPetTheme {
        HelpSupportScreen(
            onBack = {}
        )
    }
}
