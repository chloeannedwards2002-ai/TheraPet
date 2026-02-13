package com.example.therapet.app.ui.components.fields.appt

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therapet.app.ui.components.buttons.general.CustomFilledButton
import com.example.therapet.app.ui.components.buttons.general.CustomOutlinedButton
import com.example.therapet.app.ui.theme.TheraPetTheme


/**
 * @Author: Chloe Edwards
 * @Date: 08/02/2026
 *
 * Used in patients booking appointment screen - used to filter appointments
 *
 * Created with the help of MonthPicker code: https://github.com/developerchunk/Month-Year-Picker-Jetpack-Compose/tree/main/app/src/main/java/com/example/monthpickerjc/ui/theme
 */

@Composable
fun MonthPicker(
    visible: Boolean,
    currentMonth: Int,
    currentYear: Int,
    confirmButtonClicked: (Int, Int) -> Unit,
    cancelClicked: () -> Unit
) {
    if (!visible) return

    val months = listOf(
        "JAN","FEB","MAR","APR","MAY","JUN",
        "JUL","AUG","SEP","OCT","NOV","DEC"
    )

    var selectedMonth by remember { mutableStateOf(currentMonth) }
    var selectedYear by remember { mutableStateOf(currentYear) }

    AlertDialog(
        onDismissRequest = cancelClicked,
        title = { Text("Filter month and year") },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "-",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable { selectedYear-- }
                            .padding(8.dp)
                    )
                    Text(
                        text = selectedYear.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "+",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable { selectedYear++ }
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    itemsIndexed(months) { index, monthName ->
                        MonthButton(
                            monthName = monthName,
                            selected = selectedMonth == index,
                            onClick = { selectedMonth = index }
                        )
                    }
                }
            }
        },
        confirmButton = {
            CustomOutlinedButton(
                modifier = Modifier,
                onClick = { confirmButtonClicked(selectedMonth + 1, selectedYear) },
                text ="Confirm"
            )
        },
        dismissButton = {
            CustomOutlinedButton(
                modifier = Modifier,
                onClick = cancelClicked,
                text = "Cancel"
            )
        },
        containerColor = Color.White,
    )
}

@Composable
fun MonthButton(
    monthName: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = if (selected)
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        else
            ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = monthName,
            style = MaterialTheme.typography.labelSmall,
            color = if (selected) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MonthPickerPreview() {
    TheraPetTheme{
    var showPicker by remember { mutableStateOf(true) }
    var selectedMonth by remember { mutableStateOf(1) }
    var selectedYear by remember { mutableStateOf(2026) }

    MonthPicker(
        visible = showPicker,
        currentMonth = selectedMonth - 1,
        currentYear = selectedYear,
        confirmButtonClicked = { month, year ->
            selectedMonth = month
            selectedYear = year
            showPicker = false
        },
        cancelClicked = {
            showPicker = false
        }
    )
    }
}