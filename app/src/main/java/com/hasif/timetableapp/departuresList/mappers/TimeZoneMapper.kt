package com.hasif.timetableapp.departuresList.mappers

import com.hasif.timetableapp.departuresList.data.DateTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class TimeZoneMapper @Inject constructor() {

    operator fun invoke(dateTime: DateTime): String {
        val date: Date = Date(dateTime.timestamp * 1000L) // Convert timestamp to milliseconds
        val timeZone: TimeZone = TimeZone.getTimeZone(dateTime.timeZone)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        calendar.timeZone = timeZone

        val formatter: SimpleDateFormat = SimpleDateFormat("HH:mm")
        return formatter.format(calendar.time)
    }
}