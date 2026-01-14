package com.example.therapet.app.ui.screens.pet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.ui.components.bars.BasicTopBar
import com.example.therapet.app.ui.components.CircularCarousel
import com.example.therapet.app.ui.theme.TheraPetTheme
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.therapet.R
import com.example.therapet.app.ui.components.buttons.general.CustomElevatedButton
import com.example.therapet.app.ui.components.fields.input.CustomOutlinedTextField
import com.example.therapet.app.ui.components.pet.PetPenguin
import com.example.therapet.app.ui.theme.PetColours
import com.example.therapet.app.ui.viewmodel.PetViewModel



/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * CreatePet screen UI
 */

@Composable
fun CreatePetScreen(
    selectedColourIndex: Int,
    onColourSelected: (Int) -> Unit,
    onCreatePet: () -> Unit,
    modifier: Modifier = Modifier
) {
    var petName by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("create_pet_screen"),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CreatePetTopBar(
            onBack = {},
            modifier = Modifier.testTag("create_pet_top_bar")
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PetPenguin(
                bodyColour = PetColours[selectedColourIndex],
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 50.dp)
                    .testTag("pet_penguin")
            )

            Spacer(modifier = Modifier.height(130.dp))

            Text(
                text = stringResource(R.string.choose_colour),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("choose_colour_text"),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )

            CircularCarousel(
                selectedIndex = selectedColourIndex,
                onIndexChanged = onColourSelected
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.choose_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("choose_name_text"),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )

            PetNameInputField(
                petName = petName,
                onNameChanged = { petName = it },
                modifier = Modifier
                 .testTag("pet_name_input")
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ResetButton(
                    onClick = {
                        petName = ""
                        onColourSelected(0)
                    },
                    modifier = Modifier.weight(1f)
                        .testTag("reset_button")
                )
                ConfirmButton(
                    onClick = onCreatePet,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("pet_confirm_button")
                )
            }
        }
    }
}

// Top bar
@Composable
fun CreatePetTopBar(
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier){
    BasicTopBar(
        text = "Create your pet",
        onBackClick = null,
        modifier = modifier
    )
}

// Name input field
@Composable
fun PetNameInputField(
    petName: String,
    onNameChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){
    CustomOutlinedTextField(
        value = petName,
        onValueChange = onNameChanged,
        placeholder = "Pet name",
        label = "Pet name",
        modifier = modifier
    )
}

// Reset button
@Composable
fun ResetButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    CustomElevatedButton(
        onClick = onClick,
        text = "Reset",
        modifier = modifier
            .padding(top = 20.dp)
    )
}

// Confirm button
@Composable
fun ConfirmButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    CustomElevatedButton(
        onClick = onClick,
        text = "Confirm",
        modifier = modifier
            .padding(top = 20.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun CreatePetPreview() {
    TheraPetTheme {
        CreatePetScreen(
            selectedColourIndex = 0,
            onColourSelected = {},
            onCreatePet = {}
        )
    }
}