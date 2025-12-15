package com.example.therapet.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight

/* This custom calendar was created using: https://medium.com/@kiwi47/create-a-flexible-and-customizable-calendar-view-in-android-with-jetpack-compose-56dfb911c2ab */


private fun Date.dayString(): String =
    SimpleDateFormat("d", Locale.getDefault()).format(this)

private fun Calendar.copy(): Calendar =
    Calendar.getInstance().also { it.time = this.time }

@Composable
private fun WeekdayCell(dayOfWeek: Int) {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, dayOfWeek)
    }

    val label =
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) ?: ""

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun CalendarDayCell(
    date: Date,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.primaryContainer
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isToday && !isSelected) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        CircleShape
                    )
            )
        }

        Text(
            text = date.dayString(),
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Composable
private fun CalendarGridLayout(
    modifier: Modifier = Modifier,
    horizontalGap: Dp = 2.dp,
    verticalGap: Dp = 2.dp,
    content: @Composable () -> Unit
) {
    val hGapPx = with(LocalDensity.current) { horizontalGap.roundToPx() }
    val vGapPx = with(LocalDensity.current) { verticalGap.roundToPx() }

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val totalWidth = constraints.maxWidth - (hGapPx * 6)
        val cellSize = totalWidth / 7

        val placeables = measurables.map {
            it.measure(
                constraints.copy(
                    minWidth = cellSize,
                    maxWidth = cellSize,
                    minHeight = cellSize,
                    maxHeight = cellSize
                )
            )
        }

        val rows = (placeables.size + 6) / 7
        val height = rows * cellSize + (rows - 1) * vGapPx

        layout(constraints.maxWidth, height) {
            placeables.forEachIndexed { index, placeable ->
                val row = index / 7
                val col = index % 7

                val x = col * (cellSize + hGapPx)
                val y = row * (cellSize + vGapPx)

                placeable.placeRelative(x, y)
            }
        }
    }
}


@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier,
    initialDate: Date = Date(),
    startFromSunday: Boolean = true,
    onDateSelected: (Date) -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var currentMonth by remember {
        mutableStateOf(Calendar.getInstance().apply {
            time = initialDate
            set(Calendar.DAY_OF_MONTH, 1)
        })
    }

    val today = remember { Calendar.getInstance() }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { currentMonth.add(Calendar.MONTH, -1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "Previous month",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            val monthTitle = SimpleDateFormat(
                "MMMM yyyy",
                Locale.getDefault()
            ).format(currentMonth.time)

            Text(
                text = monthTitle,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium
            )

            IconButton(
                onClick = { currentMonth.add(Calendar.MONTH, 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Next month",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }


        CalendarGridLayout {

            val weekdays =
                if (startFromSunday)
                    listOf(
                        Calendar.SUNDAY,
                        Calendar.MONDAY,
                        Calendar.TUESDAY,
                        Calendar.WEDNESDAY,
                        Calendar.THURSDAY,
                        Calendar.FRIDAY,
                        Calendar.SATURDAY
                    )
                else
                    listOf(
                        Calendar.MONDAY,
                        Calendar.TUESDAY,
                        Calendar.WEDNESDAY,
                        Calendar.THURSDAY,
                        Calendar.FRIDAY,
                        Calendar.SATURDAY,
                        Calendar.SUNDAY
                    )

            weekdays.forEach { WeekdayCell(it) }

            val firstDayOfMonth = currentMonth.copy()
            val firstWeekday = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)

            val offset =
                if (startFromSunday)
                    firstWeekday - Calendar.SUNDAY
                else
                    (firstWeekday + 5) % 7

            repeat(offset.coerceAtLeast(0)) {
                Spacer(modifier = Modifier)
            }

            val daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

            for (day in 1..daysInMonth) {
                val dayCalendar = currentMonth.copy().apply {
                    set(Calendar.DAY_OF_MONTH, day)
                }

                val isSelected =
                    dayCalendar.timeInMillis == Calendar.getInstance().apply {
                        time = selectedDate
                    }.timeInMillis

                val isToday =
                    dayCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                            dayCalendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)

                CalendarDayCell(
                    date = dayCalendar.time,
                    isSelected = isSelected,
                    isToday = isToday
                ) {
                    selectedDate = dayCalendar.time
                    onDateSelected(dayCalendar.time)
                }
            }
        }
    }
}
