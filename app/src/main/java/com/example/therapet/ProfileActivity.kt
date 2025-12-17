package com.example.therapet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.MyReadOnlyEditableField
import com.example.therapet.ui.theme.ProfileAvatar
import com.example.therapet.ui.theme.TheraPetTheme

// Profile screen

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(

    ){ innerPadding ->

        BasicTopBar(
            text = "Profile",
            onBackClick = onBack,
        )

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
                .fillMaxSize()
                .testTag("profile_screen"),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(120.dp))

            ProfileAvatar()

            Spacer(modifier = Modifier.height(60.dp))

            MyReadOnlyEditableField(
                "First name",
                onEditClick = { /* TODO: eventually add editing */}
            )

            Spacer(modifier = Modifier.height(40.dp))

            MyReadOnlyEditableField(
                "Surname",
                onEditClick = { /* TODO: eventually add editing */}
            )

            Spacer(modifier = Modifier.height(40.dp))

            MyReadOnlyEditableField(
                "Patient ID",
                onEditClick = { /* TODO: eventually add editing */}
            )

            Spacer(modifier = Modifier.height(40.dp))

            MyReadOnlyEditableField(
                "Mobile",
                onEditClick = { /* TODO: eventually add editing */}
            )

            Spacer(modifier = Modifier.height(40.dp))

            MyReadOnlyEditableField(
                "Password",
                onEditClick = { /* TODO: eventually add editing */}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    TheraPetTheme {
        ProfileScreen(
            onBack = {}
        )
    }
}