package com.example.therapet.app.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.YearMonth
import java.time.ZoneId

/** In the database, appointments are stored with appointmentDateTime: Long
but the UI uses YearMonth, Room cannot query with this so this helper converts
YearMonth to Long
 **/

// An extension to YearMonth that returns a pair: The start and end of a month
fun YearMonth.toMillisRange(): Pair<Long, Long> {
    // Start = first of any month
    val start = atDay(1)
        // Turns the date into a Long
        .atStartOfDay(ZoneId.systemDefault())
        // Converts the EXACT moment into a Long (to the millisecond)
        .toInstant()
        .toEpochMilli()

    // A similar process for the end of the month
    val end = atEndOfMonth()
        .atTime(23, 59, 59, 999_000_000) // Set to the very last moment of the last day of the month
        // Same as before ^^
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    // Reutnr both values
    return start to end
}