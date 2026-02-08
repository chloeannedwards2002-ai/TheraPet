package com.example.therapet.app.ui.components.fields.appt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.therapet.app.data.model.AppointmentType
import network.chaintech.kmp_date_time_picker.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton
import com.example.therapet.app.ui.theme.TheraPetTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

/**
 * @Author: Chloe Edwards
 * @Date: 07/02/2026
 *
 * Adding appointments dialog
 */

@Composable
fun AddAppointmentDialog(
    onDismiss: () -> Unit,
    onConfirm: (Long, AppointmentType) -> Unit
) {
    var step by remember { mutableStateOf(1) }
    var selectedType by remember { mutableStateOf<AppointmentType?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (step == 1) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "New appointment",
                            style = MaterialTheme.typography.titleMedium
                        )

                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }
                }

                if (step == 1) {
                    AppointmentTypeDropdown(
                        selected = selectedType,
                        onSelected = { selectedType = it }
                    )

                    NextButton(
                        enabled = selectedType != null,
                        onClick = { step = 2 }
                    )
                }

                if (step == 2) {
                    WheelDateTimePickerView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("date_time_picker"),
                        showDatePicker = true,
                        height = 200.dp,
                        rowCount = 3,
                        yearsRange = 2024..2030,
                        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
                        onDoneClick = { selectedDateTime ->
                            val millis = selectedDateTime
                                .toInstant(TimeZone.currentSystemDefault())
                                .toEpochMilliseconds()

                            onConfirm(millis, selectedType!!)
                            onDismiss()
                        },
                        onDismiss = {}
                    )
                }
            }
        }
    }
}

@Composable
fun NextButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        CustomOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Next",
            onClick = {
                if (enabled) {
                    onClick()
                }
            }
        )
}

@Preview(showBackground = true)
@Composable
fun AddAppointmentDialogStep1Preview() {
    TheraPetTheme {
        AddAppointmentDialog(
            onDismiss = {},
            onConfirm = { _, _ -> }
        )
    }
}




