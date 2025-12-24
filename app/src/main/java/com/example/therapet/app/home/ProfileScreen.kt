package com.example.therapet.app.home

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
import androidx.compose.ui.res.stringResource
import com.example.therapet.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.ProfileAvatar
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.components.fields.read_only.ReadOnlyEditableField
import com.example.therapet.app.ui.components.fields.read_only.ReadOnlyField
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Profile screen UI
 */

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onEditPassword: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(

    ){ innerPadding ->

        BasicTopBar(
            text = stringResource(R.string.profile),
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

            ReadOnlyEditableField(
                stringResource(R.string.first_name),
                onEditClick = { /* TODO: eventually add editing */},
                modifier = modifier.testTag("edit_first_name_field"),
                editTestTag = "first_name_edit_button"
            )

            Spacer(modifier = Modifier.height(40.dp))

            ReadOnlyEditableField(
                stringResource(R.string.surname),
                onEditClick = { /* TODO: eventually add editing */},
                modifier = modifier.testTag("edit_surname_field"),
                editTestTag = "surname_edit_button"
            )

            Spacer(modifier = Modifier.height(40.dp))

            ReadOnlyField(
                stringResource(R.string.patient_id),
                modifier = modifier.testTag("patient_id_field"),
            )

            Spacer(modifier = Modifier.height(40.dp))

            ReadOnlyEditableField(
                stringResource(R.string.mobile),
                onEditClick = { /* TODO: eventually add editing */},
                modifier = modifier.testTag("edit_mobile_field"),
                editTestTag = "mobile_edit_button"
            )

            Spacer(modifier = Modifier.height(40.dp))

            ReadOnlyEditableField(
                stringResource(R.string.password),
                onEditClick = onEditPassword,
                modifier = modifier.testTag("edit_password_field"),
                editTestTag = "reset_password_button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    TheraPetTheme {
        ProfileScreen(
            onBack = {},
            onEditPassword = {}
        )
    }
}