package com.example.therapet

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
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.IconPosition
import com.example.therapet.ui.theme.MyOutlinedButtonIcon
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun HelpSupportScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
){
    Scaffold(
    ) {
        innerPadding ->

        BasicTopBar(
            text = "Help & Support",
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
                text = "Or get in touch on social media",
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
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Call Us",
        icon = {
            Icon(Icons.Default.Phone, contentDescription = null)
        },
        onClick = { }
    )
}

@Composable
fun EmailUsButton(modifier: Modifier = Modifier){
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Email us",
        icon = {
            Icon(Icons.Default.Email, contentDescription = null)
        },
        onClick = { }
    )
}

@Composable
fun InstagramButton(modifier: Modifier = Modifier){
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Instagram",
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun FacebookButton(modifier: Modifier = Modifier){
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Facebook",
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun WhatsappButton(modifier: Modifier = Modifier){
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Whatsapp",
        icon = {
            Icon(Icons.Default.Circle, contentDescription = null)
        },
        onClick = { },
        iconPosition = IconPosition.START,
    )
}

@Composable
fun TwitterButton(modifier: Modifier = Modifier){
    MyOutlinedButtonIcon(
        modifier = modifier,
        text = "Twitter",
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
