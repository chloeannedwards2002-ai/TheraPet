package com.example.therapet


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therapet.ui.theme.BasicTopBar
import com.example.therapet.ui.theme.CircularCarousel
import com.example.therapet.ui.theme.MyElevatedButton
import com.example.therapet.ui.theme.MyOutlinedTextField
import com.example.therapet.ui.theme.TheraPetTheme

@Composable
fun CreatePetScreen(
    onCreatePet: () -> Unit,
    modifier: Modifier = Modifier
) {
    var petName by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("create_pet_screen"),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CreatePetTopBar(onBack = {})

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PetPlaceholder()
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Choose colour",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            CircularCarousel(
                selectedIndex = selectedIndex,
                onIndexChanged = { selectedIndex = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Choose name",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            PetNameInputField(
                petName = petName,
                onNameChanged = { petName = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ResetButton(
                    onClick = {
                        petName = ""
                        selectedIndex = 0
                    },
                    modifier = Modifier.weight(1f)
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
fun CreatePetTopBar(onBack: (() -> Unit)? = null){
    BasicTopBar(
        text = "Create your pet",
        onBackClick = null,
    )
}

// !! PLACEHOLDER PET SMILEY FACE FOR NOW TODO: Create a fully designed pet
@Composable
fun PetPlaceholder(size: TextUnit = 260.sp){
    Text(
        text = "\uD83D\uDE0A",
        fontSize = size,
        style = MaterialTheme.typography.labelLarge
    )
}

// Name input field
@Composable
fun PetNameInputField(
    petName: String,
    onNameChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){
    MyOutlinedTextField(
        value = petName,
        onValueChange = onNameChanged,
        placeholder = "Pet name",
        label = "Pet name",
        modifier = modifier
            .testTag("pet_name_input")
    )
}

// Reset button
@Composable
fun ResetButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    MyElevatedButton(
        onClick = onClick,
        text = "Reset",
        modifier = modifier
            .padding(top = 20.dp)
            .testTag("reset_button")
    )
}

// Confirm button
@Composable
fun ConfirmButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    MyElevatedButton(
        onClick = onClick,
        text = "Confirm",
        modifier = modifier
            .padding(top = 20.dp)
            .testTag("confirm_button")
    )
}


@Preview(showBackground = true)
@Composable
fun CreatePetPreview() {
    TheraPetTheme {
        CreatePetScreen(
            onCreatePet = {}
        )
    }
}